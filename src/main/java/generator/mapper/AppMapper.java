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
    @Select("select * from app where app_name like CONCAT('%',#{appName},'%') ")
    List<App> findByName (String appName);
    //种类查找
    @Select("select * from app where app_type like CONCAT('%',#{appType},'%') ")
    List<App> findByType (String appType);
    //应用列表
    @Select("select * from app order by app_scorers desc,installations_count desc limit #{s},#{e}")
    List<App> selectList(int s,int e);
    @Select("select count('app_id') from app")
    int appsNumber();
    //更改应用状态
    @Update("UPDATE app SET state=#{state} WHERE app_id = #{appId};")
    int UpdateApp(App app);
    //增加下载次数
    @Update("UPDATE app SET installations_count=installations_count+1 WHERE app_id = #{appId};")
    int addDownloadCount(int appId);
    //获取存储路径
    @Select("select app_url from app where app_id=#{appId}")
    String getAppUrl(int  appId);

}




