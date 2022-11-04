package generator.controller;


import generator.entity.Collection;
import generator.entity.Res;
import generator.entity.User;
import generator.mapper.UserMapper;
import generator.service.CollectionService;
import generator.util.CONSTANT;
import generator.util.JwtUtil;
import generator.util.md5;
import generator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpServletResponse response;
    @ResponseBody
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++API+++++++++++++++++++++++++++++++++++++++++++++++++++++
    // 通过异常处理器方法统一返回响应结果
    @ExceptionHandler(Exception.class)
    public Res handleException(Exception E){
        response.setStatus(400);
        return Res.fail(E.getMessage());
    }
    @PostMapping("uploadPortrait")
    public Res uploadPortrait(@RequestParam("portrait") MultipartFile fileUpload,HttpServletRequest request) throws Exception{
        //获取文件名
        int uID=(int)JwtUtil.parseJWT(request.getHeader("token")).get("id");
        String fileName = "userPortrait_"+uID+".png";
        String tmpFilePath = CONSTANT.portrait_storage_path;
        String resourcesPath = tmpFilePath + "//" + fileName;
        File upFile = new File(resourcesPath);
        fileUpload.transferTo(upFile);
        userServiceImpl.uploadPortrait(resourcesPath,uID);
        return Res.success("upload successfully");
    }
    @PostMapping("getPortrait")
    public String getPortrait (HttpServletResponse response,HttpServletRequest request) throws Exception{
        int uid =(int)JwtUtil.parseJWT(request.getHeader("token")).get("id");
        File file = new File(userServiceImpl.getPortrait(uid));
        byte[] bytes = new byte[1024];

        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        while ((fis.read(bytes)) != -1) {
            os.write(bytes);
            os.flush();
        }
        return "success";
    }
    //-----------------用户列表查询---------------
    @PostMapping("list")
    public Res  UserList( int pages, int rows) {
        List<User> users=  userServiceImpl.userList(pages,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("userListInfo",users);
        map.put("maxPage",(userMapper.usersNumber()+rows-1)/rows);
        return Res.success("query successfully",map);
    }
    //-----------------用注册---------------
    @PostMapping("register")
    public Res Register(String account , String password){
        if(account.trim().equals("")||password.trim().equals("")){
            return Res.fail("missing parameter");
        }else if(userServiceImpl.is_repeated(account)){
            return Res.fail("the account has been registered");
        }
        userServiceImpl.register(account,md5.getMD5String(password));
        return Res.success("registered successfully",true);
    }
    //-----------------信息修改---------------
    @PostMapping("update")
    public Res<Object> update(HttpServletRequest request,User user) throws Exception{
        String token=request.getHeader("token");
        int id=(int) JwtUtil.parseJWT(token).get("id");
        user.setId(id);
        System.out.println(user==null);
        if(userServiceImpl.update_general(user)>=1){
            return Res.success("update completed",true);
        }
       return Res.fail("update fail");
    }
    @PostMapping("managerUpdate")
    public Res<Object> managerUpdate(HttpServletRequest request,User user){
        String token=request.getHeader("token");
        String account=(String) JwtUtil.parseJWT(token).get("account");
        if(userServiceImpl.update_managerial(user,account)>=1){
           return Res.success("update completed",true);
        }
       return Res.fail("update fail");
    }
    //-----------------删除用户---------------
    @PostMapping("delete")
    public Res<Object> deleteUser(int id){
        if(userServiceImpl.delete(id)>=1){
            return Res.success("successfully delete",true);
        }
        return Res.success("fail to delete",true);
    }
    @PostMapping("collectedAppList")
    public Object appList(HttpServletRequest request) {
        int uid=(int)JwtUtil.parseJWT(request.getHeader("token")).get("id");
        List<Collection> list = collectionService.collectedList(uid);

        return Res.success("查询成功", list);
    }

 }
