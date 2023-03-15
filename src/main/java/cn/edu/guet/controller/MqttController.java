package cn.edu.guet.controller;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/mqtt")
public class MqttController {


    @Autowired
    IMqttSender mqttSender;

    @Autowired
    Gson gson;

    //这个是外面配置文件里面的设置的接收主题之一
    private final static String SEND_TOPIC_PREFIX = "receive_iot_topic/";


    @ApiOperation("向指定主题发送消息")
    @PostMapping("/sendToTopic")
    public ApiResult sendToTopic(String topic, String payload) {
        /**
         * 想接收方方法消息-主题：receive_iot_topic/#,receive_chat_topic/#
         */
        mqttSender.sendToMqtt(topic,payload);
        System.out.println("发送成功=>" + "主题：" + topic + "  载荷:" + payload);
        return ApiResult.success(null, "发送成功");
    }


    /**
     * 127.0.0.1:8081/mqtt/control_command
     * post、json
     * {
     *   "createtime": "2022-04-17T07:02:23.707Z",
     *   "deviceid": "001设备",
     *   "humi": 30,
     *   "light": 55,
     *   "loraid": "r001",
     *   "sensorid": "123456789",
     *   "temp": 100,
     *   "types": "esp32"
     * }
     */
    @ApiOperation("模拟硬件发送的数据或控制指令")
    @PostMapping("/control_command")
    public ApiResult controlCommand(@RequestBody IotData iotData) {
        String deviceId = iotData.getDeviceid();
        // 前缀 + 设备号
        String topic = SEND_TOPIC_PREFIX + deviceId;
        String payload=gson.toJson(iotData);
        mqttSender.sendToMqtt( topic,payload);
        System.out.println("发送成功=>" + "主题：" + topic + "  载荷:" + payload);
        return ApiResult.success(null, "发送成功");
    }


}

