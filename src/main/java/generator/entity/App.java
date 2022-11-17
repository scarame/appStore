package generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName app
 */
@TableName(value ="app")
@Data
public class App implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer app_id;
    private Integer state;

    private String app_icon;
    private String edition;
    private String app_name;
    private String app_url;
    private String developers;
    private String app_type;
    private String app_introduction;
    private String app_web;
    private String app_notice;
    private String app_scorers;
    private String comment_count;
    private String installations_count;
    private String app_size;

    private int imgCurrIndex;
    private int imgNum;
    private String app_time;
    private String app_update;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        App other = (App) that;
//        return (this.getApp_id() == null ? other.getApp_id() == null : this.getApp_id().equals(other.getApp_id()))
//            && (this.getEdition() == null ? other.getEdition() == null : this.getEdition().equals(other.getEdition()))
//            && (this.getApp_name() == null ? other.getApp_name() == null : this.getApp_name().equals(other.getApp_name()))
//            && (this.getApp_url() == null ? other.getApp_url() == null : this.getApp_url().equals(other.getApp_url()))
//            && (this.getApp_time() == null ? other.getApp_time() == null : this.getApp_time().equals(other.getApp_time()))
//            && (this.getApp_update() == null ? other.getApp_update() == null : this.getApp_update().equals(other.getApp_update()))
//            && (this.getDevelopers() == null ? other.getDevelopers() == null : this.getDevelopers().equals(other.getDevelopers()))
//            && (this.getApp_type() == null ? other.getApp_type() == null : this.getApp_type().equals(other.getApp_type()))
//            && (this.getApp_introduction() == null ? other.getApp_introduction() == null : this.getApp_introduction().equals(other.getApp_introduction()))
//            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
//            && (this.getApp_web() == null ? other.getApp_web() == null : this.getApp_web().equals(other.getApp_web()))
//            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
//            && (this.getApp_notice() == null ? other.getApp_notice() == null : this.getApp_notice().equals(other.getApp_notice()))
//            && (this.getApp_size() == null ? other.getApp_size() == null : this.getApp_size().equals(other.getApp_size()))
//            && (this.getApp_scorers() == null ? other.getApp_scorers() == null : this.getApp_scorers().equals(other.getApp_scorers()))
//            && (this.getComment_count() == null ? other.getComment_count() == null : this.getComment_count().equals(other.getComment_count()))
//            && (this.getInstallations_count() == null ? other.getInstallations_count() == null : this.getInstallations_count().equals(other.getInstallations_count()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getApp_id() == null) ? 0 : getApp_id().hashCode());
//        result = prime * result + ((getEdition() == null) ? 0 : getEdition().hashCode());
//        result = prime * result + ((getApp_name() == null) ? 0 : getApp_name().hashCode());
//        result = prime * result + ((getApp_url() == null) ? 0 : getApp_url().hashCode());
//        result = prime * result + ((getApp_time() == null) ? 0 : getApp_time().hashCode());
//        result = prime * result + ((getApp_update() == null) ? 0 : getApp_update().hashCode());
//        result = prime * result + ((getDevelopers() == null) ? 0 : getDevelopers().hashCode());
//        result = prime * result + ((getApp_type() == null) ? 0 : getApp_type().hashCode());
//        result = prime * result + ((getApp_introduction() == null) ? 0 : getApp_introduction().hashCode());
//        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
//        result = prime * result + ((getApp_web() == null) ? 0 : getApp_web().hashCode());
//        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
//        result = prime * result + ((getApp_notice() == null) ? 0 : getApp_notice().hashCode());
//        result = prime * result + ((getApp_size() == null) ? 0 : getApp_size().hashCode());
//        result = prime * result + ((getApp_scorers() == null) ? 0 : getApp_scorers().hashCode());
//        result = prime * result + ((getComment_count() == null) ? 0 : getComment_count().hashCode());
//        result = prime * result + ((getInstallations_count() == null) ? 0 : getInstallations_count().hashCode());
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", appId=").append(appId);
//        sb.append(", edition=").append(edition);
//        sb.append(", appName=").append(appName);
//        sb.append(", appUrl=").append(appUrl);
//        sb.append(", appTime=").append(appTime);
//        sb.append(", appUpdate=").append(appUpdate);
//        sb.append(", developers=").append(developers);
//        sb.append(", appType=").append(appType);
//        sb.append(", appIntroduction=").append(appIntroduction);
//        sb.append(", img=").append(img);
//        sb.append(", appWeb=").append(appWeb);
//        sb.append(", state=").append(state);
//        sb.append(", appNotice=").append(appNotice);
//        sb.append(", appSize=").append(appSize);
//        sb.append(", appScorers=").append(appScorers);
//        sb.append(", commentCount=").append(commentCount);
//        sb.append(", installationsCount=").append(installationsCount);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}