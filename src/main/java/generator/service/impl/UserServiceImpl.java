package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.User;
import generator.service.UserService;
import generator.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service("UserServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> USER_LIST(int m,int n) {
        return userMapper.userList(m,n);
    }

    @Override
    public void register(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public void update(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void M_update(User user) {
        userMapper.updateUser_Manager(user);
    }

    @Override
    public void delete(int id) {
        userMapper.deleteUser(id);
    }

    @Override
    public User check(String n) {
       return userMapper.checkName(n);
    }
}




