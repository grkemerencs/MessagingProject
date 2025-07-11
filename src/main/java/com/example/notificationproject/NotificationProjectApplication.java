package com.example.notificationproject;

import com.example.notificationproject.Enum.DevicePlatform;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.notificationproject.entity.Device;
import com.example.notificationproject.repository.DeviceRepository;
import com.example.notificationproject.entity.Template;
import com.example.notificationproject.repository.TemplateRepository;

@SpringBootApplication
public class NotificationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationProjectApplication.class, args);
    }

}
