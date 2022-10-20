package generator.mapper;

import generator.entity.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import generator.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Entity generator.entity.LoginLog
 */
@Mapper
@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    @Select("call insert_log(#{login_account},#{login_ip},#{login_machine},#{login_time},@oid)")
    int login_log(String login_account ,String login_ip,String login_machine,String login_time);
    @Update("update login_log set login_out_time=#{time} where  isNull(login_out_time) and login_account=#{account}")
    void notLogout(String time,String account);
    @Update("update login_log set login_out_time=#{time} where id=#{id}")
    void logout_log(String time ,int id);
}




