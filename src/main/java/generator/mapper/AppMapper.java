package generator.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import generator.entity.App;
import org.apache.ibatis.annotations.Delete;
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
    @Select("call app_list(#{column},#{sort},#{s},#{e})")
    List<App> selectList(String column,String sort,int s,int e);
    @Select("select count('app_id') from app")
    int appsNumber();
    //修改应用信息
    @Update("UPDATE app SET" +
            "edition=#{edition}," +
            "app_name=#{app_name}," +
            "app_url=#{app_url}," +
            "developers=#{developers}," +
            "app_type=#{app_type}," +
            "app_introduction=#{app_introduction}," +
            "img=#{img}," +
            "app_web=#{app_web}," +
            "app_notice=#{app_notice}," +
            "app_size=#{app_size}," +
            "WHERE app_id = #{app_id};")
    App UpdateAppInfo(App app);
    //更改应用状态
    @Update("UPDATE app SET state=#{state} WHERE app_id = #{app_id};")
    int UpdateApp(App app);
    //增加下载次数
    @Update("UPDATE app SET installations_count=installations_count+1 WHERE app_id = #{appId};")
    int addDownloadCount(int appId);
    //获取存储路径
    @Select("select app_url from app where app_id=#{appId}")
    String getAppUrl(int  appId);
    //删除
    @Delete("DELETE FROM collection WHERE user_id = #{app_id};" +
            "DELETE FROM app WHERE app_id = #{app_id}")
    int deleteApp(int app_id);
}




