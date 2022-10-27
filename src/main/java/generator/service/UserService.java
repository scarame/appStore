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

    int update_general(User user) throws Exception;
    int update_managerial(User user,String menderAccount);
    int uploadPortrait(String url,int id);
    String getPortrait(int id);
    List<User> userList(int pages,int rows);
    int delete(int id);

}
