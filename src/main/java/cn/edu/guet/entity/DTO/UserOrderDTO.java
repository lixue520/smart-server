package cn.edu.guet.entity.DTO;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * (UserOrder)DTO类
 *
 * @author DavidNan
 * @since 2023-04-23 00:38:07
 */
@Data
public class UserOrderDTO extends Page {

    private Integer id;

    private Integer money;

    private Integer amount;

    private Integer userId;

    private String states;

    private Date createTime;

    private Date updateTime;

}
