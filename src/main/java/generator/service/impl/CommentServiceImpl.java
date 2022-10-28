package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.Comments;
import generator.mapper.CommentsMapper;
import generator.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentsMapper, Comments>
    implements CommentService {

    @Autowired
    private CommentsMapper commentsMapper;

    private List<Comments> tempReplys = new ArrayList<>();

   // private List<Comments> scTemReplys=new ArrayList<>();
    @Override
    public List<Comments> listComment(int appId) {

        List<Comments> gamescoreList = commentsMapper.findByParentIdNull(Long.parseLong("-1"), appId);
        for (Comments gamescore : gamescoreList) {
            long id = gamescore.getId();
            appId = gamescore.getAppid();
            String parentNickname1 = gamescore.getUsername();

            List<Comments> childgame = commentsMapper.findParentIdNotNull(id,appId);
            //查询出子评论
            combineChildren(childgame, parentNickname1);
            gamescore.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return gamescoreList;
    }
    private void combineChildren(List<Comments> childComments, String parentNickname1) {
        //判断是否有一级子回复

        //System.out.println("  "+childComments.size()+"零级父评论回复数组长度");
        if (childComments.size() > 0) {
            //循环找出子评论的id


            //问题所在地
            for (Comments childComment : childComments) {

                String parentNickname = childComment.getUsername();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                int childId = childComment.getId();
                //查询二级以及所有子集回复
                recursively(childId, parentNickname);
            }
        }

    }

    private void recursively(int childId, String parentNickname1) {

        List<Comments> replayComments = commentsMapper.findByReplayId(childId);
        if (replayComments.size() > 0) {

            for (Comments replayComment : replayComments) {
                System.out.println("  " + replayComment.getId() + "二级子评论id");
                System.out.println("  " + replayComment.getReplyComments().size() + "二级子评论数组长度");
                String parentNickname = replayComment.getUsername();
                replayComment.setParentNickname(parentNickname1);
                int replayId = replayComment.getId();

                tempReplys.add(replayComment);
                //循环迭代找出子集回复
                recursively(replayId, parentNickname);
            }
        }

    }
    @Override
    public int saveComment(Comments comments) {
        comments.setScore_time(new Date());
        return commentsMapper.insert(comments);
    }
}




