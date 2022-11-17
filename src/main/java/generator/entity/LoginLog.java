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
 * @TableName login_log
 */
@TableName(value ="login_log")
@Data
public class LoginLog implements Serializable {
    /**
     * ID, 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 登录IP
     */
    private String login_ip;

    /**
     * 登录机器名
     */
    private String login_machine;

    /**
     * 登录时间
     */
    private Date login_time;

    /**
     * 登录账号
     */
    private String login_account;

    /**
     * 登出时间
     */
    private Date login_out_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}