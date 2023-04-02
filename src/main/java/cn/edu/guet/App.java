package cn.edu.guet;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author qin
 */
@SpringBootApplication
//@EnableScheduling//开启定时任务
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


}