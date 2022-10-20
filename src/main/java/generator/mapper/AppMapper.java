package generator.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import generator.entity.App;
import generator.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Entity generator.domain.App
 */
@Mapper
@Repository
public interface AppMapper extends BaseMapper<App> {
    @Select("select id,userName,account where account=#{account} and passWord=#{passWord}")
    User login(String account , String passWord);
}




