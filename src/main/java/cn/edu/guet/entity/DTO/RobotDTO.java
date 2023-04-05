package cn.edu.guet.entity.DTO;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * (Robot)DTO类
 *
 * @author DavidNan
 * @since 2023-04-16 16:05:20
 */
@Data
public class RobotDTO extends Page {

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
