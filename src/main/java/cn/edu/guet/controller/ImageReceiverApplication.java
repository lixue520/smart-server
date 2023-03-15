package cn.edu.guet.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/home/image/")
public class ImageReceiverApplication {



    @PostMapping
    public ResponseEntity<String> receiveImage(@RequestBody MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Image data is missing", HttpStatus.BAD_REQUEST);
        }

        // 保存图像数据到指定目录
        byte[] imageBytes = file.getBytes();
        Path imagePath = Paths.get("/home/image/image11.jpg");
        Files.write(imagePath, imageBytes);

        return new ResponseEntity<>("Image received", HttpStatus.OK);
    }
}