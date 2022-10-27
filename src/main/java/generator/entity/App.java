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
    private Integer appId;

    /**
     * 
     */
    private String edition;

    /**
     * 
     */
    private String appName;

    /**
     * 
     */
    private String appUrl;

    /**
     * 
     */
    private Date appTime;

    /**
     * 
     */
    private Date appUpdate;

    /**
     * 
     */
    private String developers;

    /**
     * 
     */
    private String appType;

    /**
     * 
     */
    private String appIntroduction;

    /**
     * 
     */
    private String img;

    /**
     * 
     */
    private String appWeb;

    /**
     * 
     */
    private Integer state;

    /**
     * 
     */
    private String appNotice;

    /**
     * 
     */
    private Double appSize;

    /**
     * 
     */
    private String appScorers;

    /**
     * 
     */
    private String commentCount;

    /**
     * 
     */
    private String installationsCount;

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
        generator.entity.App other = (generator.entity.App) that;
        return (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getEdition() == null ? other.getEdition() == null : this.getEdition().equals(other.getEdition()))
            && (this.getAppName() == null ? other.getAppName() == null : this.getAppName().equals(other.getAppName()))
            && (this.getAppUrl() == null ? other.getAppUrl() == null : this.getAppUrl().equals(other.getAppUrl()))
            && (this.getAppTime() == null ? other.getAppTime() == null : this.getAppTime().equals(other.getAppTime()))
            && (this.getAppUpdate() == null ? other.getAppUpdate() == null : this.getAppUpdate().equals(other.getAppUpdate()))
            && (this.getDevelopers() == null ? other.getDevelopers() == null : this.getDevelopers().equals(other.getDevelopers()))
            && (this.getAppType() == null ? other.getAppType() == null : this.getAppType().equals(other.getAppType()))
            && (this.getAppIntroduction() == null ? other.getAppIntroduction() == null : this.getAppIntroduction().equals(other.getAppIntroduction()))
            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
            && (this.getAppWeb() == null ? other.getAppWeb() == null : this.getAppWeb().equals(other.getAppWeb()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getAppNotice() == null ? other.getAppNotice() == null : this.getAppNotice().equals(other.getAppNotice()))
            && (this.getAppSize() == null ? other.getAppSize() == null : this.getAppSize().equals(other.getAppSize()))
            && (this.getAppScorers() == null ? other.getAppScorers() == null : this.getAppScorers().equals(other.getAppScorers()))
            && (this.getCommentCount() == null ? other.getCommentCount() == null : this.getCommentCount().equals(other.getCommentCount()))
            && (this.getInstallationsCount() == null ? other.getInstallationsCount() == null : this.getInstallationsCount().equals(other.getInstallationsCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getEdition() == null) ? 0 : getEdition().hashCode());
        result = prime * result + ((getAppName() == null) ? 0 : getAppName().hashCode());
        result = prime * result + ((getAppUrl() == null) ? 0 : getAppUrl().hashCode());
        result = prime * result + ((getAppTime() == null) ? 0 : getAppTime().hashCode());
        result = prime * result + ((getAppUpdate() == null) ? 0 : getAppUpdate().hashCode());
        result = prime * result + ((getDevelopers() == null) ? 0 : getDevelopers().hashCode());
        result = prime * result + ((getAppType() == null) ? 0 : getAppType().hashCode());
        result = prime * result + ((getAppIntroduction() == null) ? 0 : getAppIntroduction().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        result = prime * result + ((getAppWeb() == null) ? 0 : getAppWeb().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getAppNotice() == null) ? 0 : getAppNotice().hashCode());
        result = prime * result + ((getAppSize() == null) ? 0 : getAppSize().hashCode());
        result = prime * result + ((getAppScorers() == null) ? 0 : getAppScorers().hashCode());
        result = prime * result + ((getCommentCount() == null) ? 0 : getCommentCount().hashCode());
        result = prime * result + ((getInstallationsCount() == null) ? 0 : getInstallationsCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", appId=").append(appId);
        sb.append(", edition=").append(edition);
        sb.append(", appName=").append(appName);
        sb.append(", appUrl=").append(appUrl);
        sb.append(", appTime=").append(appTime);
        sb.append(", appUpdate=").append(appUpdate);
        sb.append(", developers=").append(developers);
        sb.append(", appType=").append(appType);
        sb.append(", appIntroduction=").append(appIntroduction);
        sb.append(", img=").append(img);
        sb.append(", appWeb=").append(appWeb);
        sb.append(", state=").append(state);
        sb.append(", appNotice=").append(appNotice);
        sb.append(", appSize=").append(appSize);
        sb.append(", appScorers=").append(appScorers);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", installationsCount=").append(installationsCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}