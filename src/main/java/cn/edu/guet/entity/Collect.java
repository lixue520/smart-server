package cn.edu.guet.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Collect)实体类
 *
 * @author DavidNan
 * @since 2023-04-19 13:18:15
 */
@Data
@TableName(value = "collect")
public class Collect {

    private Integer id;

    private String ph;

    private String tds;

    private String temp;

    private String flow1;

    private String flow;

    private String height;

    private String height1;

    private String bottle;

    private Integer dzf;

    private Integer dzf1;

    private Integer pump;

    private Integer pump1;

    private String csb;

    private Integer robotId;
    /**
     * 建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    private Integer user_id;

    private Integer beep;

    private Integer zwLed;

    private String types;

    private Integer status;

    private String devicdId;

}
