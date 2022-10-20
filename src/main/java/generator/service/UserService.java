package generator.service;


import com.baomidou.mybatisplus.extension.service.IService;
import generator.entity.User;
import java.util.List;

/**
 *
 */
public interface UserService extends IService<User> {
    User login(String account,String passWord);
    void register(String account,String passWord);
    boolean is_repeated(String account);

    int update_general(User user);
    int update_managerial(User user,String menderAccount);
    List<User> userList(int pages,int rows);
    int delete(int id);

}
