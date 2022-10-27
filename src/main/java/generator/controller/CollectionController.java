package generator.controller;

import generator.entity.Collection;
import generator.entity.Res;
import generator.service.CollectionService;
import generator.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("app")
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @RequestMapping("collectApp")
    public  Object collectApp(Collection game)throws Exception{
        if(collectionService.collect(game)==1) {

            return Res.success("下载成功", true);
        }
        else {
            return Res.fail("已有数据");
        }
    }
    @RequestMapping("collectedAppList")
    public Object appList(HttpServletRequest request) {
        int uid=(int)JwtUtil.parseJWT(request.getHeader("token")).get("id");
        List<Collection> list = collectionService.collectedList(uid);

        return Res.success("查询成功", list);
    }



}
