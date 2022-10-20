package generator.service;


import com.baomidou.mybatisplus.extension.service.IService;
import generator.entity.App;
import generator.entity.User;


/**
 *
 */
public interface AppService extends IService<App> {
    User login(String account, String passWord);
    void register(String account,String passWord);
}
