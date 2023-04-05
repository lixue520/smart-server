package cn.edu.guet.entity;

import lombok.Data;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/4/25 13:49
 * {"money":5,"pay_url":"weixin://wxpay/bizpayurl?pr\u003dU5TLiauzz","target":"pay_url"}
 */
@Data
public class paydata {
    private Integer money;
    private String pay_url;
    private String target;
    //private int v2_enable;//防止多次更新二维码

}
