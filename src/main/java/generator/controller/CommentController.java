package generator.controller;

import generator.entity.Comments;
import generator.entity.Res;
import generator.service.CommentService;
import generator.util.CONSTANT;
import generator.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("comments")
public class
CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("AppCommentList")
    public Object AppCommentList(int appId){
        List<Comments> find = commentService.listComment(appId);
        return Res.success("query successfully",find) ;
    }


    @PostMapping("addComments")
    public Object addComments(Comments comments,HttpServletRequest request)throws Exception{
        String token=request.getHeader("token");
        comments.setUsername(JwtUtil.parseJWT(token).get("userName").toString());
        comments.setUserid((int)JwtUtil.parseJWT(token).get("id"));
        comments.setScore_time(CONSTANT.getCurrentTime());
        commentService.saveComment(comments);
        return Res.success("评论成功",true);

    }

}


