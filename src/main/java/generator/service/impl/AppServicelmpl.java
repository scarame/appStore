package generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.App;
import generator.mapper.AppMapper;
import generator.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
