package cn.edu.guet.entity.VO;

import java.util.Date;

import lombok.Data;

/**
 * (User)VO类
 *
 * @author DavidNan
 * @since 2023-04-16 16:07:32
 */
@Data
public class UserVO {

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
