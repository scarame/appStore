package generator.controller;

import generator.entity.Res;
import generator.entity.User;
import generator.entity.md5;
import generator.service.UserService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userServiceImpl;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++API+++++++++++++++++++++++++++++++++++++++++++++++++++++
    //-----------------用户列表查询---------------
    @RequestMapping("list")
    public Res<List<User>>  UserList(int page,int row) {
        List<User> users= userServiceImpl.USER_LIST((page-1)*row,row);
        return Res.success("查询成功！",true,users);
    }
    //-----------------用注册---------------
    @PostMapping("register")
    public Res Register(User user){
       Res res= UserController.IU_Judge(user,userServiceImpl.check(user.getName()),"注册成功！");
       if(res.getSuccess()){
           user.setPwd(md5.getMD5String(user.getPwd()));
           userServiceImpl.register(user);
       }
       return res;
    }
    //-----------------登录---------------
    @PostMapping("login")
    public Res<Object> login(User u){
        u.setPwd(md5.getMD5String(u.getPwd()));
        User user =  userServiceImpl.login(u);
        if(user!=null){
          return Res.success("登录成功",user);
        }
        return Res.fail("登陆失败！");
    }
    //-----------------信息修改---------------
    @PostMapping("update")
    public Res<Object> update(User user,int m_status){
        User u1= userServiceImpl.check(user.getName());
        Res res= UserController.IU_Judge(user,u1,"修改成功");
        if(!res.getSuccess()){
            return res;
        }
        user.setPwd(md5.getMD5String(user.getPwd()));
        if(m_status<3){
            userServiceImpl.update(user);
            return Res.success("修改成功！",true);
        }
        try{
            if(user.getStatus().equals("")){
                return Res.fail("用户权限不能设置为空");
            }
        }catch (Exception e){return Res.fail("用户权限不能设置为空");}

        userServiceImpl.M_update(user);
        return Res.success("篡改成功！",true);
    }
    //-----------------删除用户---------------
    @RequestMapping("delete")
    public Res<Object> deleteUser(int id){
        userServiceImpl.delete(id);
        return Res.success("用户删除成功！",true);
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++方法区+++++++++++++++++++++++++++++++++++++++++++++++++++++
    //==================判断(插入/修改)是否合法=====================
    public static Res IU_Judge(User user,User u1,String mes){
        try {
            if(user.getName().equals("")||user.getPwd().equals("")){
                return Res.fail("用户属性不能为空！");
            }
            if(u1!=null&&u1.getId()!=user.getId()){
                return Res.fail("该用户名已存在！");
            }
        } catch (Exception e) {
            return Res.fail("用户属性不能为Null");
        }
        return Res.success(mes,true);
    }
    //======================权限判断===============================
    public static Res StatusJudge(User user,User u1,String mes){
        try {
            if(user.getName().equals("")||user.getPwd().equals("")){
                return Res.fail("用户属性不能为空！");
            }
            if(u1!=null&&u1.getId()!=user.getId()){
                return Res.fail("该用户名已存在！");
            }
        } catch (Exception e) {
            return Res.fail("用户属性不能为Null");
        }
        return Res.success(mes,true);
    }
}
