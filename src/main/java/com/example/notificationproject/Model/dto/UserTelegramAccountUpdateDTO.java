package com.example.notificationproject.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTelegramAccountUpdateDTO {
    private String fromName;
    private long chatId;
} 