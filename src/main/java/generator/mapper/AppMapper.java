package generator.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import generator.entity.App;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    //id查找
    @Select("select * from app where app_id=#{appId}")
    List<App> findById (int appId);
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
    @Select("select app_url from app where app_id=#{appId};")
    String getAppUrl(int  appId);
    //删除
    @Delete("DELETE FROM app WHERE app_id = #{app_id}")
    int deleteApp(int app_id);
    //存图标
    @Update("update app set app_icon=#{path} where app_id=#{appId};")
    int updateIcon(String path,int appId);
    //返回图片下标
    @Select("call app_add_img(#{maxIndex},#{appId},@a,@b)")
    Map addImg(int maxIndex, int appId);
    @Select("select imgCurrIndex,imgNum from app where app_id=#{appId}")
    Map getImgInfo(int appId);
    @Update("update app set imgNum =if(imgNum>0,imgNum-1,imgNum) where app_id=#{appId};")
    int deleteImg(int appId);
    @Update("update app set app_size=${size},edition=#{edition},app_url=#{appName} where app_id=#{appId};")
    void uploadApp(int appId,String size,String edition,String appName);

    @Update("update app set app_notice=#{name} where app_id=#{appId};")
    void test(int appId,String name);
}




