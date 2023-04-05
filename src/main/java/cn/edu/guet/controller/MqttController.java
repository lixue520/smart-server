package cn.edu.guet.controller;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.guet.entity.IotData;
import cn.edu.guet.mqtt.ApiResult;
import cn.edu.guet.mqtt.IMqttSender;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/12 0:57
 */

@Api(tags = "MqttController", description = "MQTT测试接口")
@RestController
@RequestMapping("/emqx")
public class MqttController {

    private static final Logger logger = LoggerFactory.getLogger(MqttController.class);

    @Autowired
    IMqttSender mqttSender;

    @Autowired
    Gson gson;

    //这个是外面配置文件里面的设置的接收主题之一
    private final static String SEND_TOPIC_PREFIX = "smartwater/";


    @PostMapping("/sendToTopic")
    public ApiResult sendToTopic(String topic, String payload) {
        /**
         * 想接收方方法消息-主题：receive_iot_topic/#,receive_chat_topic/#
         */
        topic = SEND_TOPIC_PREFIX + topic;
        mqttSender.sendToMqtt(topic, payload);
        logger.info("Send_ok=>" + " Topic:" + topic + "  payload:" + payload);
        return ApiResult.success(null, "发送成功");
    }


    //通用接收接口,发送格式如下：
    //http://localhost:8086/emqx/SendByTopic
    //    {
    //            "topic": "test",
    //            "types": "Esp8266",
    //            "data": {"user_name": "11", "user_id": 1}
    //    }
    @PostMapping("/SendByTopic")
    public ApiResult controlCommand(@RequestBody IotData iotData) {
        String topics = iotData.getTopic();
        // 前缀 + topic
        String topic = SEND_TOPIC_PREFIX + topics;
        String payload = gson.toJson(iotData);
        mqttSender.sendToMqtt(topic, payload);
        logger.info("Send_ok=>" + " Topic:" + topic + "  payload:" + payload);
        return ApiResult.success(null, "Send_Ok");
    }

}

