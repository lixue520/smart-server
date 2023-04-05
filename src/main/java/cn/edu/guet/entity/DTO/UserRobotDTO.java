package cn.edu.guet.entity.DTO;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * (UserRobot)DTO类
 *
 * @author DavidNan
 * @since 2023-04-16 16:07:53
 */
@Data
public class UserRobotDTO extends Page {
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
