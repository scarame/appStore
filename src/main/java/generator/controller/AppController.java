package generator.controller;


import generator.entity.App;
import generator.entity.Res;
import generator.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("app")
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("List")
    public Res appList(int pages,int rows) {

        List<App> list = appService.appList(pages,rows);
        return Res.success("query successfully",list);
    }
    @PostMapping("findByName")
    public Res findAppByName(String appName){
        List<App> name = appService.findByName(appName);
       return Res.success("query successfully",name);
    }
    @PostMapping("findByType")
    public Object findAppByType(String appType){
        List<App> appList = appService.findByType(appType);
        return Res.success("query successfully",appList);
    }

    @PostMapping("update")
    public Object update(App app){
        appService.updateApp(app);
        return Res.success("update completed",app);
    }
}