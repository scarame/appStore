package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.User;
import generator.service.UserService;
import generator.mapper.UserMapper;
import generator.util.CONSTANT;
import generator.util.md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public User login(String account, String passWord){
       User user=userMapper.login(account,passWord);
        return user;
    }
    @Override
    public void register(String account, String passWord){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        userMapper.register(account,passWord,account,dateFormat.format(date));

    }
    @Override
    public boolean is_repeated(String account) {
        return  null!=userMapper.is_repeated(account);
    }

    @Override
    public int update_general(User user){
        System.out.println(user);
        user.setPassword(md5.getMD5String(user.getPassword()));
        return userMapper.update_general(user, CONSTANT.getCurrentTime());
    }
    @Override
    public int update_managerial(User user, String menderAccount){
        user.setPassword(md5.getMD5String(user.getPassword()));
        return userMapper.update_managerial(user,menderAccount,CONSTANT.getCurrentTime());
    }
    @Override
    public int uploadPortrait(String url,int id) {
        return userMapper.updatePhoto(url,id);
    }

    @Override
    public String getPortrait(int id) {
        return userMapper.getPhoto(id);
    }

    @Override
    public List<User> userList(int pages, int rows){
        return userMapper.userList((pages-1)*rows,rows);
    }
    @Override
    public int delete(int id){
        return userMapper.userDelete(id);
    }

}




