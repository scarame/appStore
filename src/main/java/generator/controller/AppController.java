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
    @PostMapping("delete")
    public Object deleteApp(int app_id){
        appService.deleteApp(app_id);
        return Res.success("successfully delete");
    }
    @GetMapping("getIcon")
    public String getIcon (String src) throws Exception{
        File file = new File(src);
        byte[] bytes = new byte[1024];
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        while ((fis.read(bytes)) != -1) {
            os.write(bytes);
            os.flush();
        }
        return "success";
    }
    @GetMapping("uploadIcon")
    public Res uploadIcon (@RequestParam("portrait") MultipartFile fileUpload,int appId) throws Exception{
        int appID=appId;
        String fileName = "appIcon_"+appID+".png";
        String tmpFilePath = CONSTANT.app_icon_path;
        String resourcesPath = tmpFilePath + "//" + fileName;
        File upFile = new File(resourcesPath);
        fileUpload.transferTo(upFile);
        appService.uploadAppIcon(resourcesPath,appID);
        return Res.success("upload successfully");
    }
    @PostMapping("download")
    public Res getFile(HttpServletRequest request, HttpServletResponse response, int appId) throws Exception{
        if(request.getHeader("token")!=null){
            int uid=(int)JwtUtil.parseJWT(request.getHeader("token")).get("id");
            if(collectionService.collect(uid,appId)!=1) {
                return Res.success("已经下载该软件", true);
            }
        }
        File readFile = new File(appService.getAppUrl(appId));
        //字节流-用于读文件  这里只是demo用的非缓冲流。实际项目可以用BufferedInputStream。
        FileInputStream fileInputStream = new FileInputStream(readFile);//字节流
        //设置文件下载方式
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(appService.getAppUrl(appId),"utf-8"));
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
        return Res.success("下载成功");
    }
}