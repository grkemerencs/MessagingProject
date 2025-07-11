package com.example.notificationproject.dto.request;

import com.example.notificationproject.Enum.Channel;
import com.example.notificationproject.entity.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationRequestDTO {

    private List<String> deviceTokens;

    private List<String> deviceIds;

    private List<String> emails;
    private List<String> emailIds;
    @NotNull
    private Channel channel;

    @NotNull
    private String templateId;

    private Map<String, String> parameters;
}