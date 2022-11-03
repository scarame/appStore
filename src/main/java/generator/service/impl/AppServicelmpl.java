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
    public List<App> appList(int pages,int rows) {
        return mapper.selectList((pages-1)*rows,rows);
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
    public int updateApp(App app) {
        return mapper.UpdateApp(app);
    }
    @Override
    public String getAppUrl(int appId) {
        return mapper.getAppUrl(appId);
    }
}
