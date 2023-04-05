package cn.edu.guet.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Robot)实体类
 *
 * @author DavidNan
 * @since 2023-04-16 16:05:19
 */
@Data
@TableName(value = "robot")
public class Robot {

    private Integer id;

    private String name;

    private String address;

    private String topicSub;

    private String topicPub;
    /**
     * 建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
