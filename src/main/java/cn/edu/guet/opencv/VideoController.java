package cn.edu.guet.opencv;

import cn.edu.guet.controller.CameraController;
import cn.edu.guet.entity.bottleData;
import cn.edu.guet.mqtt.IMqttSender;
import com.google.gson.Gson;

import org.opencv.core.Mat;

import org.opencv.imgcodecs.Imgcodecs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//1.功能1,由于每次上传视频流都保存成video-stream.jpeg。这样就可以动态调用opencv用来做图像处理。
//2.功能2,接收来自ESP32-CAM的视频流并合成mp4在后台动态保存，但是要加定时任务，这是为了避免云服务器资源被占用
@RestController
public class VideoController {

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
    @Autowired
    IMqttSender mqttSender;

    @Autowired
    Gson gson;


    @PostMapping(value = "/video-stream",produces = "image/jpeg")
    public byte[] saveVideoStream(HttpServletRequest request, @RequestBody byte[] data) throws IOException {
        // Save the uploaded file to the file system
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        String filename = "video-stream.jpeg";
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\qin\\Desktop\\code\\smart-server\\" + filename);
        outputStream.write(data);
        outputStream.close();


        // Read the JPEG image from the request body
        ServletInputStream inputStream = request.getInputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            // Process the incoming data in the buffer
        }
        inputStream.close();

        Mat img = Imgcodecs.imread("/home/image/" + filename);


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = now.format(formatter);

        bottleData payload = new bottleData();//设置载荷

        payload.setCreatetime(s);
        payload.setStatus(0);
        payload.setDeviceid("炜哥1号");
        payload.setLevel((float) 0.01);

        mqttSender.sendToMqtt("smartwater/esp32-cam",gson.toJson(payload));//
        logger.info("ESP32-CAM send is ok!");
        return new byte[0];

    }

    @GetMapping("/stream")//需要不断刷新请求
    public ResponseEntity<byte[]> getImage() throws IOException {
        Path path = Paths.get("/home/image/video-stream.jpeg");
        byte[] imageBytes = Files.readAllBytes(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

}