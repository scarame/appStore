package generator.mapper;

import generator.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Entity generator.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //用户列表
    @Select("select ID,Name,Status from user")
    List<User> userList();
    //登录
    @Select("select ID,Name,Status,PWD from user where Name=#{name} and PWD=#{pwd}")
    User login(User user);
    //添加用户
    @Insert("insert into user (Name, PWD) values(#{name}, #{pwd})")
    void addUser(User user);
    //删除用户
    @Delete("delete from user where id = #{id}")
    void deleteUser(int id);
    //用户信息更改
    @Update("update user set Name = #{name},PWD=#{pwd},Status=#{status} where ID = #{id}")
    void updateUser(User user);

}




