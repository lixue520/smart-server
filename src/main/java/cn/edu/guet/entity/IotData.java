package cn.edu.guet.entity;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/12 0:53
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @param <T> User user = new User();
 *            // 设置user的属性...
 *            IotData<User> iotData = new IotData<>("topic", "types", user);
 */
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IotData<T> implements Serializable {
    public String topic;//话题
    public String types;//设备类型
    public String user_name;//用户
    public T data;

}