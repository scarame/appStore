package generator.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import generator.entity.App;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity generator.domain.App
 */
@Mapper
@Repository
public interface AppMapper extends BaseMapper<App> {
    //关键字查找
    @Select("select * from app where appname like CONCAT('%',#{appName},'%') ")
    List<App> findByName (String appName);
    //种类查找
    @Select("select * from app where apptype like CONCAT('%',#{appType},'%') ")
    List<App> findByType (String appType);
    //应用列表
    @Select("select * from app order by app_scorers desc,installations_count desc limit #{s},#{e}")
    List<App> selectList(int s,int e);
    @Update("UPDATE app SET state=#{state} WHERE app_id = #{appId};")
    int UpdateApp(App app);





}




