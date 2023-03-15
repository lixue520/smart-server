package cn.edu.guet.controller;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/13 19:23
 */
import cn.edu.guet.entity.bottleData;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import cn.edu.guet.mqtt.IMqttSender;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 简单的用于wifi传图接口
 * {
 *     "createtime":"时间戳",
 *     "deviceid":"0号机",
 *     "Level":"0.0%",
 *     "status":0,(0,1)
 * }
 */
@Controller
public class CameraController {

    @Autowired
    IMqttSender mqttSender;

    @Autowired
    Gson gson;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestBody byte[] imageBytes) throws IOException {
        System.out.println("--------kaishi--------");
        String filePath = "C:/Users/qin/Desktop/code/opencvtest/b2.png";  //上传服务器的时候记得改一下吧，发布的时候用注解来传。
        File file = new File(filePath);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imageBytes);
        fos.close();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = now.format(formatter);

        bottleData payload = new bottleData();//设置载荷

        payload.setCreatetime(s);
        payload.setStatus(0);
        payload.setDeviceid("0号机");
        payload.setLevel((float) 0.01);

        mqttSender.sendToMqtt("smartwater/001设备",gson.toJson(payload));//
        System.out.println("Image uploaded successfully");
        return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
    }

}

