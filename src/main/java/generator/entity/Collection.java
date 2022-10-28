package generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName collection
 */
@TableName(value ="collection")
@Data
public class Collection implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer user_id;

    /**
     * 
     */
    private Integer app_id;

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
        generator.entity.Collection other = (generator.entity.Collection) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getApp_id() == null ? other.getApp_id() == null : this.getApp_id().equals(other.getApp_id()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getApp_id() == null) ? 0 : getApp_id().hashCode());
        return result;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", userId=").append(userId);
//        sb.append(", appId=").append(appId);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}