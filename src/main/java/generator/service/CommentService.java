package generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import generator.entity.Comments;

import java.util.List;

/**
 *
 */
public interface CommentService extends IService<Comments> {
    //查询父级评论列表
    List<Comments> listComment();
    //保存评论
    int saveComment(Comments comments);
    //查询评论
    List<Comments> listFind(Comments comments);
}
