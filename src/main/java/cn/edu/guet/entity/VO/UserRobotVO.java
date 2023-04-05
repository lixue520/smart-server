package cn.edu.guet.entity.VO;

import java.util.Date;

import lombok.Data;

/**
 * (UserRobot)VO类
 *
 * @author DavidNan
 * @since 2023-04-16 16:07:53
 */
@Data
public class UserRobotVO {
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
