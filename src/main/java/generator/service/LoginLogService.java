package generator.service;

import generator.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface LoginLogService extends IService<LoginLog> {
        int login_log(String account,String ip);
        void logout_log();
        void logout_log(int index);
        void notLogout(String time,String account);
}
