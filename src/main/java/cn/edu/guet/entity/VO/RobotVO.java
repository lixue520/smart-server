package cn.edu.guet.entity.VO;

import java.util.Date;

import lombok.Data;

/**
 * (Robot)VO类
 *
 * @author DavidNan
 * @since 2023-04-16 16:05:20
 */
@Data
public class RobotVO {

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
