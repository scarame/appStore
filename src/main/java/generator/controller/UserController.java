package generator.controller;

import com.baomidou.mybatisplus.extension.api.R;
import generator.entity.Result;
import generator.entity.User;
import generator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userServiceImpl;
    @RequestMapping("list")
    public List<User> UserList(Model model){
        List<User> users= userServiceImpl.USER_LIST();
        model.addAttribute("goods",users);
        return users;
    }
    @RequestMapping("register")
    public User Register(User user){
        userServiceImpl.register(user);
        return user;
    }
    @RequestMapping("login")
    public Object login(User user){
      if(userServiceImpl.login(user)!=null){
          Result result=new Result();
          result.success(1,"登录成功",user);
         return result;
      }
        return null;
    }

    @RequestMapping("update")
    public User update(User user){
        userServiceImpl.update(user);
        return null;
    }

    @RequestMapping("delete")
    public void deleteUser(int id){
        userServiceImpl.delete(id);
    }
}
