package cn.edu.guet.controller;
import cn.edu.guet.entity.bottleData;
import cn.edu.guet.entity.response.ResponseBean;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import cn.edu.guet.mqtt.IMqttSender;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/13 19:23
 * 远程接口调用测试
 */
/**
 * 简单的用于wifi传图接口
 * {
 *     "createtime":"时间戳",
 *     "deviceid":"0号机",
 *     "Level":"0.0%",
 *     "status":0,(0,1)
 * }
 */
@RestController
@RequestMapping("opencv")
public class CameraController {

    private static final Logger logger = LoggerFactory.getLogger(CameraController.class);
    @Autowired
    IMqttSender mqttSender;

    @Autowired
    Gson gson;

    @Autowired
    private RestTemplate restTemplate;

    String opencv_url = "http://112.124.10.167:8082/opencv/";//远端opencv-web服务程序

    @PostMapping(value = "/video-stream", produces = "image/jpeg")
    public ResponseEntity<byte[]> opencv_send(HttpServletRequest request, @RequestBody byte[] data) throws Exception {
        logger.info("up to server");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpEntity<byte[]> requestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<byte[]> response = restTemplate.postForEntity(opencv_url + "video-stream", requestEntity, byte[].class);
        byte[] img = response.getBody();
        HttpHeaders responseHeaders = response.getHeaders();
        HttpStatus status = response.getStatusCode();
        ResponseEntity<byte[]> result = new ResponseEntity<>(img, responseHeaders, status);
        return result;
    }


    @GetMapping("/src")
    ResponseEntity<byte[]> opencv_src() throws Exception {

        ResponseEntity<byte[]> response = restTemplate.exchange(opencv_url + "src", HttpMethod.GET, null, byte[].class);
        byte[] img = response.getBody();
        HttpHeaders headers = response.getHeaders();
        HttpStatus status = response.getStatusCode();
        ResponseEntity<byte[]> result = new ResponseEntity<>(img, headers, status);
        String filename = "bb7.jpg";
        // FileOutputStream outputStream = new FileOutputStream("src/main/resources/static/" + filename);
        FileOutputStream outputStream = new FileOutputStream(filename);
        outputStream.write(img);
        outputStream.close();
        return result;
    }

    @GetMapping("/end")
    ResponseEntity<byte[]> opencv_end() throws Exception {

        ResponseEntity<byte[]> response = restTemplate.exchange(opencv_url + "end", HttpMethod.GET, null, byte[].class);
        byte[] img = response.getBody();
        HttpHeaders headers = response.getHeaders();
        HttpStatus status = response.getStatusCode();
        ResponseEntity<byte[]> result = new ResponseEntity<>(img, headers, status);
        return result;
    }


    @RequestMapping(value = "testOpencvGet", method = RequestMethod.GET)
    public ResponseBean testGet() {
        //请求地址
        String url = "http://localhost:8082/testGet";

        //发起请求,直接返回对象
        ResponseBean responseBean = restTemplate.getForObject(url, ResponseBean.class);
        System.out.println(responseBean.toString());
        return responseBean;
    }

    @RequestMapping(value = "testGetByRestFul", method = RequestMethod.GET)
    public void testGetByRestFul() {
        //请求地址
        String url = "http://localhost:8082/testGetByRestFul/{1}/{2}";

        //发起请求,直接返回对象（restful风格）
        ResponseBean responseBean = restTemplate.getForObject(url, ResponseBean.class, "001", "张三");
        System.out.println(responseBean.toString());
    }


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

        mqttSender.sendToMqtt("smartwater/001设备", gson.toJson(payload));//
        System.out.println("Image uploaded successfully");
        return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
    }

}

