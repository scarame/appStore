package generator.mapper;

import generator.entity.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    @Select("select * from login_log order by id desc limit #{s},#{e}")
    List<LoginLog> logList(int s, int e);
    @Select("select * from login_log where login_account=#{userName} order by id desc limit #{s},#{e}")
    List<LoginLog> inquiryUser(int s, int e,String userName);
    @Select("select max(id),min(id) from login_log")
    Map logCount();
    @Select("select count(*) from login_log where login_account=#{userName}")
    int logUserCount(String userName);
}



