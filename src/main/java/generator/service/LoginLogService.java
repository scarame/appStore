package generator.service;

import generator.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface LoginLogService extends IService<LoginLog> {
        int login_log(String account,String ip);
        void logout_log();
        void logout_log(int index);
        void notLogout(String time,String account);
        List<LoginLog> logList(int page, int rows);
        List<LoginLog> inquiryUser(int page, int rows,String userName);
}
