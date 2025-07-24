package com.example.notificationproject.config;


import com.example.notificationproject.exception.FireBaseInitilazitionException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Configuration
@Slf4j
public class FireBaseConfig {

    @Value("${firebase.auth.file}")
    String firebaseAuthFile;

    @PostConstruct
    public void initializeFireBaseAdminConnection() {
        try (InputStream serviceAccount = getClass().getClassLoader()
                .getResourceAsStream(firebaseAuthFile)) {
            if (serviceAccount == null) {
                throw new FireBaseInitilazitionException("Firebase account not found");
            }
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase admin connection has been successfully initialized");
            }
        } catch (Exception e) {
            throw new FireBaseInitilazitionException("Firebase Couldn't start");
        }
    }
}
