package cn.edu.guet.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (UserRobot)实体类
 *
 * @author DavidNan
 * @since 2023-04-16 16:07:52
 */
@Data
@TableName(value = "user_robot")
public class UserRobot {
    /**
     * cz
     */
    private Integer id;

    private Integer user_id;

    private Integer robotId;
    /**
     * 建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
