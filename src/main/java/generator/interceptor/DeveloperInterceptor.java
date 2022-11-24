package generator.interceptor;

import generator.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DeveloperInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        try {
            String token= request.getHeader("token");
            int type=(int) JwtUtil.parseJWT(token).get("userType");
            if(type>=2){
                return true;
            }
        }catch (Exception e){}
        response.setStatus(401);
        return false;
    }
}
