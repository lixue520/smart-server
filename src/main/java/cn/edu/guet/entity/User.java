package cn.edu.guet.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (User)实体类
 *
 * @author DavidNan
 * @since 2023-04-16 16:07:31
 */
@Data
@TableName(value = "user")
public class User {

    private String user_name;

    private Integer user_id;

    private String useremail;

    private String idcard;
    /**
     * 建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
