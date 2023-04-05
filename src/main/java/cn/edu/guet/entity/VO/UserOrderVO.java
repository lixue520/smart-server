package cn.edu.guet.entity.VO;

import java.util.Date;

import lombok.Data;

/**
 * (UserOrder)VO类
 *
 * @author DavidNan
 * @since 2023-04-23 00:38:07
 */
@Data
public class UserOrderVO {

    private Integer id;

    private Integer money;

    private Integer amount;

    private Integer userId;

    private String states;

    private Date createTime;

    private Date updateTime;

}
