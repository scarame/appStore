package generator.controller;


import generator.entity.Res;
import generator.entity.User;
import generator.service.LoginLogService;
import generator.service.UserService;
import generator.util.CONSTANT;
import generator.util.RequestUtil;
import generator.util.JwtUtil;
import generator.util.md5;
import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LoginLogService loginLogService;
    //=================登录====================
    @PostMapping("login")
    public Res<Object> login(HttpServletRequest request,String account,String password){
        //获取用户对象和请求ip
        try {
            User user =  userServiceImpl.login(account, md5.getMD5String(password));
            String ip= RequestUtil.getIpAddress(request);
            String tKey=(String) redisTemplate.opsForValue().get(account);
            if(tKey!=null){
                int logIndex=(int)JwtUtil.parseJWT(tKey).get("logIndex");;
                loginLogService.logout_log(logIndex);
            }
            if(user!=null&&user.getStatus()==1){
                //生成日志
                int logIndex=loginLogService.login_log(account,ip);
                //创建token；
                String token= JwtUtil.createJWT(user,ip,logIndex);
                //设置token有效期和键值；
                redisTemplate.opsForValue().set(account,token,CONSTANT.count,CONSTANT.C_TimeUnit);
                //设置返回值；
                Map<String,Object> map=new HashMap<>();
                map.put("userInfo",user);
                map.put("token",token);
                return Res.success("login successfully",map);
            }else if(user.getStatus()==0){
                return Res.fail("账号被禁用");
            }
        }catch (Exception e){
            Res.fail("错误请求");
        }

        return Res.fail("login failure");
    }


    //=================注销====================
    @PostMapping("logout")
    public Res logout(String account){
        loginLogService.logout_log();
        redisTemplate.delete(account);
        return Res.success("账号已登出.",true);
    }


    public static boolean is_repeatLogin(String curr_account,String curr_ip,RedisTemplate redis){
        if(redis.opsForValue().get(curr_account)==null){
            System.out.println("kon");
            return false;
        }
        Claims claims=JwtUtil.parseJWT((String)redis.opsForValue().get(curr_account));
        String account=claims.getSubject();
        Object ip= claims.get("ip");
        if(account.equals(curr_account)){
            System.out.println("已登录");
            return false;
        }else if(ip.equals(curr_ip)){
            System.out.println("已下线上一个账号");
            return false;
        }
        return true;
    }
}

