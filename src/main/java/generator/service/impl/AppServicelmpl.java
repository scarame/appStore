package generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.App;
import generator.mapper.AppMapper;
import generator.service.AppService;
import generator.util.CONSTANT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class AppServicelmpl extends ServiceImpl<AppMapper,App> implements AppService {
    @Autowired
    private AppMapper mapper;
    @Override
    public List<App> appList(String column,String sort,int pages,int rows) {
        pages=(pages-1)*rows;
        if(pages<0){
            pages=0;
        }
        if(column==null||sort==null){
            column="app_scorers";
            sort="desc";
        }
        return mapper.selectList(column,sort,pages,rows);
    }
    @Override
    public List<App> findByName(String appName) {
        return mapper.findByName(appName);
    }
    @Override
    public List<App> findByType(String appType) {
        return mapper.findByType(appType);
    }
    @Override
    public List<App> findById(int appId) {return mapper.findById(appId); }
    @Override
    public App updateAppInfo(App app) {
        return mapper.UpdateAppInfo(app);
    }

    @Override
    public int updateApp(App app) {
        return mapper.UpdateApp(app);
    }
    @Override
    public String getAppUrl(int appId) {
        return mapper.getAppUrl(appId);
    }
    @Override
    public void addDownloadCount(int appId) {
        mapper.addDownloadCount(appId);
    }

    @Override
    public int deleteApp(int app_id) {
        return mapper.deleteApp(app_id);
    }

    @Override
    public int uploadAppIcon(String path, int appId) {
        return mapper.updateIcon(path,appId);
    }

    @Override
    public int[]  addImg(int maxIndex, int appId) {
        Map map=mapper.addImg(maxIndex,appId);
        int[] res={(int)map.get("oid"),(int)map.get("Inum")};
        return res;
    }
    @Override
    public int[] getImgInfo(int appId) {
        Map map=mapper.getImgInfo(appId);
        int[] res={(int)map.get("imgCurrIndex"),(int)map.get("imgNum")};
        return res;
    }
    @Override
    public int deleteImg(int appId) {
        return mapper.deleteImg(appId);
    }

    @Override
    public void uploadApp(int appId, String size, String edition,String appName) {
         mapper.uploadApp(appId,size,edition,appName);
    }

    @Override
    public int addApp(App app) {
        if(app.getApp_url()==null){
            app.setApp_url("");
        }
        app.setApp_time(CONSTANT.getCurrentTime());
        return mapper.addApp(app);
    }

    public static String  customModification(App app) throws Exception{
        Field[] fieldList = app.getClass().getDeclaredFields();
        String fieldStr="", str="", name="";
        try {
            for (int i=2;i<14;i++){
                fieldList[i].setAccessible(true);
                str=(String) fieldList[i].get(app);
                name=fieldList[i].getName();
                fieldStr=fieldStr + (str==null?"":(name+"='"+str+"',"));
            }
        }catch (Exception e){}
        return fieldStr.substring(0,fieldStr.length()-1);
    }
}
