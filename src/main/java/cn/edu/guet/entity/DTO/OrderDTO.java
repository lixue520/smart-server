package cn.edu.guet.entity.DTO;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * (Order)DTO类
 *
 * @author DavidNan
 * @since 2023-04-23 00:07:01
 */
@Data
public class OrderDTO extends Page {

    private Integer id;

    private Integer amount;

    private String money;

    private String address;

    private String states;
    /**
     * 建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    private Integer userId;

}
