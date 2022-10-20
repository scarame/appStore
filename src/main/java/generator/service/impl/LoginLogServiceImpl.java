package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.LoginLog;
import generator.service.LoginLogService;
import generator.mapper.LoginLogMapper;
import generator.util.CONSTANT;
import generator.util.JwtUtil;
import generator.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog>
    implements LoginLogService{
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Override
    public int login_log( String account,String ip) {
        String os= RequestUtil.getOsInfo(request);
        return loginLogMapper.login_log(account,ip,os,CONSTANT.getCurrentTime());
    }

    @Override
    public void logout_log() {
        String currentToken = request.getHeader("token");
        int index=(int)JwtUtil.parseJWT(currentToken).get("logIndex");
        loginLogMapper.logout_log(CONSTANT.getCurrentTime(),index);
    }
    @Override
    public void logout_log(int index) {
        loginLogMapper.logout_log(CONSTANT.getCurrentTime(),index);
    }

    @Override
    public void notLogout(String time, String account) {
        loginLogMapper.notLogout(time,account);
    }

}




