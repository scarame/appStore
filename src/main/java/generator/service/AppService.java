package generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import generator.entity.App;
import java.util.List;

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
    //id查找
    List<App>  findById(int appId);
    //信息修盖
    App updateAppInfo(App app);
    //状态修改
    int updateApp (App app);
    //app下载
    String getAppUrl(int  appId);
    //增加下载次数
    void addDownloadCount(int appId);
    int deleteApp(int app_id);
    //上传app图标
    int uploadAppIcon(String path,int appId);
    //添加或覆盖预览图
    int[] addImg(int maxIndex, int appId);
    //获取预览图
    int[] getImgInfo(int appId);
    //删除预览图
    int deleteImg(int appId);
    //上传应用包
    void uploadApp(int appId,String size,String edition,String appName);
    //添加新应用
    int addApp(App app);
}
