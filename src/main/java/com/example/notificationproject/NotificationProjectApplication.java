package com.example.notificationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotificationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationProjectApplication.class, args);
    }

}
