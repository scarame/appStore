package generator.mapper;

import generator.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity generator.entity.User
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    //登录
    @Select("select id,user_photo,user_name,account,mobile,email,birthday,sex,status,user_type_id from user where account=#{account} and password=#{password}")
    User login(String account , String password);
    @Select("select id,user_photo,user_name,account,mobile,email,birthday,sex,status,user_type_id" +
            ",create_by,create_date,update_by,update_date,status from user where account=#{account}")
    User is_repeated(String account);
    //注册
    @Insert("insert into user (account,password,create_by,create_date) values(#{account},#{password},#{create_by},#{create_date})")
    void register(String account ,String password,String create_by,String create_date);
    //用户列表
    @Select("select * from user limit #{s},#{e}")
    List<User> userList(int s, int e);
    //删除用户
    @Delete("delete from user where id=#{id}")
    int userDelete(int id);
    //===更改信息====
    //普通用户
    @Update("update user set user_photo=#{user_photo},user_name=#{user_name},account=#{account},password=#{password}," +
            "email=#{email},mobile=#{mobile},birthday=#{birthday},sex=#{sex}," +
            "update_by=#{account},update_date=#{update_date} where id=#{id}")
    int update_general(User user,String date);
    //管理者
    @Update("update user set user_photo=#{user_photo},user_name=#{user_name},account=#{account},password=#{password}," +
            "email=#{email},mobile=#{mobile},birthday=#{birthday},sex=#{sex}," +
            "update_by=#{menderAccount},update_date=#{update_date} where id=#{id}")
    int update_managerial(User user,String menderAccount,String date);
}




