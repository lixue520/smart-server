package cn.edu.guet.Tcp;
import java.io.*;

public class ImageController {
    public static void main(String[] args) throws Exception{


        //测试mqtt消息发送与订阅



//        //测试获取本地时间
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String s = now.format(formatter);
//        System.out.println(s);
        File file = new File("C:\\Users\\qin\\Desktop\\code\\224-springboot-demo-mysql8-mybatisplus-xml\\src\\main\\resources\\static\\DavidNan.jpg");

        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream in = new BufferedInputStream(fileInputStream);
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] content = out.toByteArray();
        UploadImage.sshSftp(content, "Test.jpg");
    }
}


