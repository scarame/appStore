package generator.interceptor;

import generator.util.CONSTANT;
import generator.util.JwtUtil;
import generator.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class OperationInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        try {
            String token= request.getHeader("token");
            int type=(int)JwtUtil.parseJWT(token).get("userType");
            if(type>=2){
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
}
