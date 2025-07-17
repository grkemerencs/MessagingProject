package com.example.notificationproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "telegram_ıd_state")
public class TelegramIdState {
    @Id
    private String id = "singleton"; // Tek kayıt için sabit id
    private long state;


}

