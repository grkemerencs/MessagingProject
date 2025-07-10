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

    @Bean
    public CommandLineRunner loadDummyDevices(DeviceRepository deviceRepository, TemplateRepository templateRepository) {
        return args -> {
            deviceRepository.save(new Device ("Gorkem", DevicePlatform.ANDROID, "evLm1DyERXeLS4IE-6v6G"+
                    "v:APA91bEm6BnJzlBmaMZXXwMm3ZGs5_6cLM8ZE5FPvZbNG7dH46fN31XB7fP76E-X-LQpxdSIxqg3OBPZMjioHQCfcXTsIW" +
                    "m8fIyXWFPhxzGXKXJUKB8XgR8"));
            deviceRepository.save(new Device ("Mehmet", DevicePlatform.ANDROID, "token_mehmet_456"));
            deviceRepository.save(new Device( "Ayşe", DevicePlatform.ANDROID, "token_ayse_789"));
            deviceRepository.save(new Device( "Fatma", DevicePlatform.IOS, "token_fatma_101"));
            deviceRepository.save(new Device( "Can", DevicePlatform.ANDROID, "token_can_112"));
            deviceRepository.save(new Device( "Elif", DevicePlatform.IOS, "token_elif_131"));

            templateRepository.save(new Template(0, "Hoşgeldin", "Hoşgeldin, ${name}!", "Uygulamamıza katıldığın için teşekkürler, ${name}."));
            templateRepository.save(new Template(0, "Duyuru", "Duyuru: ${title}", "Sevgili kullanıcı, ${body}"));
            templateRepository.save(new Template(0, "Hatırlatma", "Hatırlatma: ${event}", "${date} tarihinde bir etkinliğiniz var."));
        };
    }

}
