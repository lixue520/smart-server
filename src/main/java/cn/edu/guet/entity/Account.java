package cn.edu.guet.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Account)实体类
 *
 * @author DavidNan
 * @since 2023-04-19 13:26:02
 */
@Data
@TableName(value = "account")
public class Account {
    /**
     * 卡ID
     */
    private Integer id;
    /**
     * 卡状态
     */
    private String ic_states;
    /**
     * 余额
     */
    private String user_money;
    /**
     * 积分
     */
    private String icIntegral;
    /**
     * 建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    private Integer user_id;

    private String user_password;

}
