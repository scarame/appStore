package generator.controller;


import generator.entity.Res;
import generator.entity.User;
import generator.util.CONSTANT;
import generator.util.JwtUtil;
import generator.util.md5;
import generator.service.UserService;
import org.junit.Test;
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
               return Res.success("query successfully",true,users);
           }catch (Exception e){
               return Res.fail("missing parameter");
           }
    }
    //-----------------用注册---------------
    @PostMapping("register")
    public Res Register(String account , String password){
        try {
            if(account.trim().equals("")||password.trim().equals("")){
                return Res.fail("missing parameter");
            }else if(userServiceImpl.is_repeated(account)){
                return Res.fail("the account has been registered");
            }
            userServiceImpl.register(account,md5.getMD5String(password));
        } catch (Exception e) {
            return Res.fail("missing parameter");
        }

       return Res.success("registered successfully",true);
    }
    //-----------------信息修改---------------
    @PostMapping("update")
    public Res<Object> update(User user) throws Exception{
        try{
            if(userServiceImpl.update_general(user)>=1){
                return Res.success("update completed",true);
            }
        }catch (Exception e){
            return Res.fail("missing parameter");
        }
       return Res.fail("update fail");
    }
    @PostMapping("managerUpdate")
    public Res<Object> managerUpdate(HttpServletRequest request,User user){
       String token=request.getHeader("token");
       String account=(String) JwtUtil.parseJWT(token).get("account");
       try{
           if(userServiceImpl.update_managerial(user,account)>=1){
               return Res.success("update completed",true);
           }
       }catch (Exception e){
           return Res.fail("missing parameter");
       }
        return Res.fail("update fail");
    }
    //-----------------删除用户---------------
    @PostMapping("delete")
    public Res<Object> deleteUser(int id){
        try {
            if(userServiceImpl.delete(id)>=1){
                return Res.success("successfully delete",true);
            }
        }catch (Exception e){
            Res.success("missing parameter",true);
        }
        return Res.success("fail to delete",true);
    }
 }
