package com.example.notificationproject.util;

import com.example.notificationproject.entity.Template;
import com.google.firebase.messaging.Notification;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public class NotificationConstructor {
    public static Notification construct(Template template, Map<String, String> parameters) {
        StringSubstitutor substitutor = new StringSubstitutor(parameters);
        String title = substitutor.replace(template.getTitle_template());
        String body = substitutor.replace(template.getBody_template());
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
    }
}
