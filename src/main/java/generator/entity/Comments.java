package generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @TableName comments
 */
@TableName(value ="comments")
@Data
public class Comments implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer appid;

    private Integer userid;

    private String username;

    private double score;

    private String content;

    private String score_time;

    private Integer parent_comment_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", appid=").append(appid);
//        sb.append(", userid=").append(userid);
//        sb.append(", username=").append(username);
//        sb.append(", score=").append(score);
//        sb.append(", scoreContent=").append(scoreContent);
//        sb.append(", scoreTime=").append(scoreTime);
//        sb.append(", parentCommentId=").append(parentCommentId);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
    private List<Comments> replyComments = new ArrayList<>();

    public void setReplyComments(List<Comments> replyComments) {
        this.replyComments = replyComments;
    }

    public List<Comments> getReplyComments() {
        return replyComments;
    }

    private generator.entity.Comments parentComment;

    public generator.entity.Comments getParentComment() {
        return parentComment;
    }

    public void setParentComment(generator.entity.Comments parentComment) {
        this.parentComment = parentComment;
    }

    private String parentNickname;

    public void setParentNickname(String parentNickname) {
        this.parentNickname = parentNickname;
    }

    public String getParentNickname() {
        return "@"+parentNickname;
    }
}