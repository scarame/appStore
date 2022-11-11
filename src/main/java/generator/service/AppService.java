package generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import generator.entity.App;

import java.util.List;

;

/**
 *
 */
public interface AppService extends IService<App> {
    //显示产品
    List<App> appList(String column,String sort,int pages,int rows);
    //关键字查找
    List<App>  findByName (String appName);
    //类别查找
    List<App>  findByType (String appType);
    //信息修盖
    App updateAppInfo(App app);
    //状态修改
    int updateApp (App app);
    //app下载
    String getAppUrl(int  appId);
    //增加下载次数
    void addDownloadCount(int appId);
    int deleteApp(int app_id);
    //获取app图标
    int uploadAppIcon(String path,int appId);
}
