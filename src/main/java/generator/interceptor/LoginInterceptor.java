package generator.interceptor;
import generator.util.CONSTANT;
import generator.util.RequestUtil;
import generator.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor{
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        try {
            String currentIP= RequestUtil.getIpAddress(request);
            String currentToken = request.getHeader("token");
            String redis_key= JwtUtil.parseJWT(currentToken).getSubject();
            Object redisToken=  redisTemplate.opsForValue().get(redis_key);
            Object redisIp=JwtUtil.parseJWT(currentToken).get("ip");

            if(redisToken.equals(currentToken)&&
            currentIP.equals(redisIp)){
                redisTemplate.expire(redis_key,CONSTANT.count, CONSTANT.C_TimeUnit);
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) {
//        System.out.println("控制器执行完毕");
//    }
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception){
//        System.out.println("返回结果"+response);
//        System.out.println("请求完毕 ");
//    }
}

