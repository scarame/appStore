package generator.service;

import generator.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface UserService extends IService<User> {
    List<User> USER_LIST(int m,int n);
    void   register(User user);
    User login(User user);
    void  update(User user);
    void  M_update(User user);
    void delete(int id);
    User  check(String n);
}
