package cn.edu.guet.entity.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.NoArgsConstructor;

/**
 * (User)DTO类
 *
 * @author DavidNan
 * @since 2023-04-16 16:07:32
 */
@Data
@AllArgsConstructor  //有参构造
@NoArgsConstructor   //无参构造
public class UserDTO extends Page {

    private String user_name;

    private Integer user_id;

    private String useremail;

    private String idcard;
    /**
     * 建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
