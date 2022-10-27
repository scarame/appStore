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

    /**
     * 
     */
    private Integer appid;

    /**
     * 
     */
    private Integer userid;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String score;

    /**
     * 
     */
    private String scoreContent;

    /**
     * 
     */
    private Date scoreTime;

    /**
     * 
     */
    private Integer parentCommentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        generator.entity.Comments other = (generator.entity.Comments) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppid() == null ? other.getAppid() == null : this.getAppid().equals(other.getAppid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getScoreContent() == null ? other.getScoreContent() == null : this.getScoreContent().equals(other.getScoreContent()))
            && (this.getScoreTime() == null ? other.getScoreTime() == null : this.getScoreTime().equals(other.getScoreTime()))
            && (this.getParentCommentId() == null ? other.getParentCommentId() == null : this.getParentCommentId().equals(other.getParentCommentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAppid() == null) ? 0 : getAppid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getScoreContent() == null) ? 0 : getScoreContent().hashCode());
        result = prime * result + ((getScoreTime() == null) ? 0 : getScoreTime().hashCode());
        result = prime * result + ((getParentCommentId() == null) ? 0 : getParentCommentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appid=").append(appid);
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", score=").append(score);
        sb.append(", scoreContent=").append(scoreContent);
        sb.append(", scoreTime=").append(scoreTime);
        sb.append(", parentCommentId=").append(parentCommentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
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