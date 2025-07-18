package com.example.notificationproject.config;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FireBaseConfig {
    @PostConstruct
    public void initializeFireBaseAdminConnection() {
        try (InputStream serviceAccount = getClass().getClassLoader()
                .getResourceAsStream("notificationtest-c9049-a5eb84b5a70e.json")) {
            if (serviceAccount == null) {
                throw new IllegalStateException("firebase-adminsdk.json bulunamadı!");
            }
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            System.out.println("Firebase başlatıldı.");
        } catch (IOException e) {
            throw new RuntimeException("Firebase başlatılamadı", e);
        }
    }
}
