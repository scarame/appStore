package generator.controller;


import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import generator.entity.App;
import generator.entity.Res;
import generator.mapper.AppMapper;
import generator.service.AppService;
import generator.service.CollectionService;
import generator.util.CONSTANT;
import generator.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("app")
public class AppController {
    @Autowired
    private AppService appService;
    @Autowired
    private AppMapper appMapper;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    HttpServletResponse response;
    // 通过异常处理器方法统一返回响应结果
    @ExceptionHandler(Exception.class)
    public Res handleException(Exception E){
        response.setStatus(400);
        return Res.fail(E.getMessage());
    }
    @PostMapping("list")
    public Res appList(String column,String sort,int pages,int rows) {
        List<App> list = appService.appList(column,sort,pages,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("appListInfo",list);
        map.put("maxPage",(appMapper.appsNumber()+rows-1)/rows);
        return Res.success("query successfully",map);
    }
    @PostMapping("findByName")
    public Res findAppByName(String appName){
        List<App> name = appService.findByName(appName);
       return Res.success("query successfully",name);
    }
    @PostMapping("findByType")
    public Object findAppByType(String appType){
        List<App> appList = appService.findByType(appType);
        return Res.success("query successfully",appList);
    }
    @GetMapping("findByID")
    public Object findByID(int app_id){
        return Res.success("query successfully",appService.findById(app_id));
    }
    @PostMapping("updateInfo")
    public Res updateInfo(App app){
        appService.updateAppInfo(app);
        return Res.success("update completed");
    }
    @PostMapping("update")
    public Object update(App app){
        appService.updateApp(app);
        return Res.success("update completed");
    }
    @RequestMapping("removeApp")
    public Object deleteApp(int app_id){
        appService.deleteApp(app_id);
        return Res.success("successfully delete");
    }
    @RequestMapping("getIcon")
    public String getIcon (String src) throws Exception{
        src = CONSTANT.app_icon_path+"//"+src+".png";
        File file = new File(src);
        byte[] bytes = new byte[1024];
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        while ((fis.read(bytes)) != -1) {
            os.write(bytes);
            os.flush();
        }
        fis.close();
        return "success";
    }
    @GetMapping("uploadIcon")
    public Res uploadIcon (@RequestParam("appIcon") MultipartFile fileUpload,int appId) throws Exception{
        String fileName = "appIcon("+appId+").png";
        String tmpFilePath = CONSTANT.app_icon_path;
        String resourcesPath = tmpFilePath + "//" + fileName;
        File upFile = new File(resourcesPath);
        fileUpload.transferTo(upFile);
        appService.uploadAppIcon(("appIcon_"+appId),appId);
        return Res.success("upload successfully");
    }
    //========================================img =================================

     static int isExist(int resArr0, int resArr1,int index){
         int max=resArr0;
         int min=0;

         try {
             min =resArr0-resArr1+10*(1-(resArr0+1)/resArr1)+1;
         }catch (Exception e){return -1;}
         int curr=(min+index)%10;
         if(min<=max){
             if(!(min<=curr&&curr<=max)){
                 return -1;
             }
         }else if(!(curr<=max||curr>=min)){
             return -1;
         }
        return min;
    }
    @RequestMapping("getImg")
    public Res getImg (int appId,int index) throws Exception{
        int[] resArr=appService.getImgInfo(appId);
        int getIndex =AppController.isExist(resArr[0],resArr[1],index);
        String src = CONSTANT.app_img_path+"//"+appId+"//"+(getIndex+index)%10+".jpg";
        File file = new File(src);
        byte[] bytes = new byte[1024];
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        while ((fis.read(bytes)) != -1) {
            os.write(bytes);
            os.flush();
        }
        fis.close();
        return Res.success("query successfully");
    }
    @RequestMapping("uploadImg")
    public Res uploadImg (@RequestParam("appImg") MultipartFile fileUpload,int appId,int index) throws Exception{
        String fileName;
        String tmpFilePath = CONSTANT.app_img_path;
        if(index==-1){
            int[] resArr=appService.addImg(10,appId);
            fileName = resArr[0]+".jpg";
        }else if(index>-1&&index<appService.getImgInfo(appId)[1]){
            fileName=index+".jpg";
        }else{return Res.fail("Invalid parameter");}

        String resourcesPath = tmpFilePath + "//"+appId+"//"+fileName;
        File upFile = new File(resourcesPath);
        fileUpload.transferTo(upFile);
        return Res.success("upload successfully");
    }
    @PostMapping("deleteImg")
    public Res deleteImg (int appId) throws Exception{
        String mes= appService.deleteImg(appId)== 1 ? "successfully delete": "Invalid operation";
        return Res.success(mes);
    }
    @RequestMapping("download")
    public Res getFile(HttpServletRequest request, HttpServletResponse response, int appId)throws IOException {
        if(request.getHeader("token")!=null){
            int uid=(int)JwtUtil.parseJWT(request.getHeader("token")).get("id");
            if(collectionService.collect(uid,appId)!=1) {
                return Res.success("已经下载该软件", true);
            }
        }
        String src=CONSTANT.app_storage_path+"//";
        String fileName=appService.getAppUrl(appId);
        File readFile = new File(src+fileName);
        //字节流-用于读文件  这里只是demo用的非缓冲流。实际项目可以用BufferedInputStream。
        FileInputStream fileInputStream = new FileInputStream(readFile);//字节流
        //设置文件下载方式
        response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
        ServletOutputStream outputStream = response.getOutputStream(); //字节流

        //流数据交换，每次交换10k数据大小 （1024k = 1m）
        byte[] bytes = new byte[1024*10];
        int read;
        do {
            read = fileInputStream.read(bytes);
            outputStream.write(bytes,0,read);
        }while (-1 != read);

        //关闭资源
        IOUtils.closeQuietly(fileInputStream);
        IOUtils.closeQuietly(outputStream);
        appService.addDownloadCount(appId);
        fileInputStream.close();
        return Res.success("successfully upload");
    }

    @PostMapping("upload")
    public Res receiveFile(@RequestParam("package") MultipartFile fileUpload,int appId,String edition)throws IOException {

        if(appService.findById(appId)==null){ return Res.fail("nonexistent app");}
        String fileName=fileUpload.getOriginalFilename();
        String resourcesPath = CONSTANT.app_storage_path + "//" + fileName;
        File upFile = new File(resourcesPath);
        fileUpload.transferTo(upFile);
        appService.uploadApp(appId,"'"+fileUpload.getSize()+"B'",edition,fileUpload.getOriginalFilename());
        return Res.success("success");
    }
    @PostMapping("addApp")
    public Res addApp(App app) {
        if(appService.addApp(app)==1){
            return Res.success("success");
        }
        return Res.fail("fail");
    }
}