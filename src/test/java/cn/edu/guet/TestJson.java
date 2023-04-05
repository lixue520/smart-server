package cn.edu.guet;

import cn.edu.guet.entity.IotData;
import cn.edu.guet.entity.User;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/4/16 18:24
 */
@SpringBootTest
class TestJson {
    private static final Logger logger = LoggerFactory.getLogger(TestJson.class);

    @Autowired
    Gson gson;


    @Test
    void contextLoads() {
        User user = new User();
        user.setUser_id(1);
        user.setUser_name("1");
        IotData<User> iotData = new IotData<>("/smartwater/test", "Esp8266", "DavidNan", user);


        System.out.println(gson.toJson(iotData));
    }

}