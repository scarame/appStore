package generator.controller;


import generator.entity.Res;

import generator.entity.User;
import generator.util.JwtUtil;
import generator.util.md5;
import generator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private RedisTemplate redisTemplate;



    //++++++++++++++++++++++++++++++++++++++++++++++++++++++API+++++++++++++++++++++++++++++++++++++++++++++++++++++
    //-----------------用户列表查询---------------
    @RequestMapping("list")
    public Res<List<User>>  UserList( int pages, int rows) {
           try {
               List<User> users=  userServiceImpl.userList(pages,rows);
               return Res.success("查询成功！",true,users);
           }catch (Exception e){
               return Res.fail("缺少参数");
           }
    }
    //-----------------用注册---------------
    @PostMapping("register")
    public Res Register(String account , String password){
        try {
            if(account.trim().equals("")||password.trim().equals("")){
                return Res.fail("用户属性不能为空");
            }else if(userServiceImpl.is_repeated(account)){
                return Res.fail("该账户已注册");
            }
            userServiceImpl.register(account,md5.getMD5String(password));
        } catch (Exception e) {
            return Res.fail("缺少用户参数");
        }

       return Res.success("注册成功",true);
    }
    //-----------------信息修改---------------
    @PostMapping("update")
    public Res<Object> update(User user) throws Exception{
        if(userServiceImpl.update_general(user)>=1){
            return Res.success("修改完成",true);
        }
       return Res.fail("update fail");
    }
    @PostMapping("managerUpdate")
    public Res<Object> managerUpdate(HttpServletRequest request,User user) throws Exception{
       String token=request.getHeader("token");
        userServiceImpl.update_managerial(user,(String) JwtUtil.parseJWT(token).get("account"));
        return Res.success("修改完成",true);
    }
    //-----------------删除用户---------------
    @RequestMapping("delete")
    public Res<Object> deleteUser(int id){
        userServiceImpl.delete(id);
        return Res.success("用户删除成功！",true);
    }
 }
