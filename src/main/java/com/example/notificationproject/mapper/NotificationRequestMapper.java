package com.example.notificationproject.mapper;

import com.example.notificationproject.Model.Aggregate.NotificationRequest;
import com.example.notificationproject.Model.dto.request.NotificationRequestDTO;
import com.example.notificationproject.exception.NotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.function.Function;


@Mapper
public interface  NotificationRequestMapper {

    NotificationRequest toDomain(NotificationRequestDTO notificationRequestDTO);
}
