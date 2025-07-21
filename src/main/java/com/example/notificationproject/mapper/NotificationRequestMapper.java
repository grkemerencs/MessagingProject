package com.example.notificationproject.mapper;

import com.example.notificationproject.Model.Aggregate.NotificationRequest;
import com.example.notificationproject.Model.dto.request.NotificationRequestDTO;
import com.example.notificationproject.exception.NotFoundException;

import java.util.function.Function;

public class NotificationRequestMapper {
    public static Function<NotificationRequestDTO, NotificationRequest> toDomain = (notificationRequestDTO)-> {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setChannel(notificationRequestDTO.getChannel());
        notificationRequest.setEmails(notificationRequestDTO.getEmails());
        notificationRequest.setEmailIds(notificationRequestDTO.getEmailIds());
        notificationRequest.setDeviceIds(notificationRequestDTO.getDeviceIds());
        notificationRequest.setDeviceTokens(notificationRequestDTO.getDeviceTokens());
        notificationRequest.setParameters(notificationRequestDTO.getParameters());
        notificationRequest.setTemplateName(notificationRequestDTO.getTemplateName());
        notificationRequest.setTelegramChatIds(notificationRequest.getTelegramChatIds());
        return notificationRequest;
    };
}
