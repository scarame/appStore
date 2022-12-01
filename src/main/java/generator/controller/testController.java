package generator.controller;

import generator.mapper.CommentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @Autowired
    CommentsMapper commentsMapper;
    @RequestMapping("test")
    public String test () throws Exception{
        for(int i=86;i<=3867;i++){

            commentsMapper.test("2022-"+
                    +(int)(Math.random()*12)+"-"+
                    (int)(Math.random()*27)+
                    " 14:14:52",i);
        }
        return "ok";
    }
}
