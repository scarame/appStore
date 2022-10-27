package generator.controller;

import generator.entity.Comments;
import generator.entity.Res;
import generator.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("comments")
public class
ConmmentsMapper {

    @Autowired
    private CommentService commentService;


    @PostMapping("AppCommentList")
    public Object AppCommentList(int appID){
        List<Comments> find = commentService.listComment();
        return find;
    }


    @PostMapping("insertscore")
    public Object insertcomments(Comments gamescore)throws Exception{

        try{

            if(gamescore.getScore().equals("")||gamescore.getScoreContent().equals("")) {

                return Res.fail("评论内容不得为空");

            } else if(gamescore.getUsername().equals("")||gamescore.getUserid().equals("")) {

                return Res.fail("请登入再评论");

            }else {

                commentService.saveComment(gamescore);

                return Res.success("评论成功",true);
            }

        }catch (Exception e){
            System.out.println(e);
            return Res.fail("评论属性不能为Null");
        }


    }

}


