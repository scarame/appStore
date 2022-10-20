package generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户头像
     */
    private String user_photo;

    /**
     * 用户姓名
     */
    private String user_name;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 机构ID
     */
    private Byte org_id;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 创建人
     */
    private String create_by;

    /**
     * 创建时间
     */
    private Date create_date;

    /**
     * 更新人
     */
    private String update_by;

    /**
     * 更新时间
     */
    private Date update_date;

    /**
     * 状态(0禁止;1启用;2删除)
     */
    private Byte status;

    /**
     * 用户类型(0普通用户,1管理员;2超级用户)
     */
    private Byte user_type_id;

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
//        User other = (User) that;
//        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
//            && (this.getUserPhoto() == null ? other.getUserPhoto() == null : this.getUserPhoto().equals(other.getUserPhoto()))
//            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
//            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
//            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
//            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
//            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
//            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
//            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
//            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
//            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
//            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
//            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
//            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
//            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
//            && (this.getUserTypeId() == null ? other.getUserTypeId() == null : this.getUserTypeId().equals(other.getUserTypeId()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
//        result = prime * result + ((getUserPhoto() == null) ? 0 : getUserPhoto().hashCode());
//        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
//        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
//        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
//        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
//        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
//        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
//        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
//        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
//        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
//        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
//        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
//        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
//        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
//        result = prime * result + ((getUserTypeId() == null) ? 0 : getUserTypeId().hashCode());
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", userPhoto=").append(userPhoto);
//        sb.append(", userName=").append(userName);
//        sb.append(", account=").append(account);
//        sb.append(", password=").append(password);
//        sb.append(", orgId=").append(orgId);
//        sb.append(", mobile=").append(mobile);
//        sb.append(", email=").append(email);
//        sb.append(", birthday=").append(birthday);
//        sb.append(", sex=").append(sex);
//        sb.append(", createBy=").append(createBy);
//        sb.append(", createDate=").append(createDate);
//        sb.append(", updateBy=").append(updateBy);
//        sb.append(", updateDate=").append(updateDate);
//        sb.append(", status=").append(status);
//        sb.append(", userTypeId=").append(userTypeId);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}