package cn.edu.guet.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (UserOrder)实体类
 *
 * @author DavidNan
 * @since 2023-04-23 00:38:06
 */
@Data
@TableName(value = "user_order")
public class UserOrder {

    private Integer id;

    private Integer money;

    private Integer amount;

    private Integer userId;

    private String states;

    private Date createTime;

    private Date updateTime;
    private String pay_url;
    private String target;
    //private int v2_enable;//防止多次更新二维码

}
