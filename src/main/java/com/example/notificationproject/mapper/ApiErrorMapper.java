package com.example.notificationproject.mapper;

import com.example.notificationproject.Enum.Channel;
import com.example.notificationproject.Enum.DevicePlatform;
import com.example.notificationproject.Model.dto.respond.ApiErrorRespondDTO;
import com.example.notificationproject.exception.BaseNotificationServiceException;
import com.example.notificationproject.exception.ErrorCodes;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.firebase.database.core.Platform;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

import java.util.Arrays;

@Mapper
public interface ApiErrorMapper {


    default ApiErrorRespondDTO ExceptionToErrorDTO(BaseNotificationServiceException exception) {
        return ApiErrorRespondDTO.builder()
                .success(false)
                .failureMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build();
    }

    default ApiErrorRespondDTO ExceptionToErrorDTO(InvalidFormatException exception) {
        String failureField = exception.getPath().get(0).getFieldName();
        String failureMessage = "Invalid "+ failureField + "parameter.\nExpected values: ";
        switch (failureField) { // in case other parameters have other enum types.
            case "channel":
                failureMessage += Arrays.toString(Channel.values());
                break;
            case "platform":
                failureMessage += Arrays.toString(DevicePlatform.values());
                break;
            default: failureMessage += "Can't find expected values for this parameter";
        }
        return ApiErrorRespondDTO.builder()
                .success(false)
                .failureMessage(failureMessage)
                .errorCode(ErrorCodes.InvalidFormatException)
                .build();
    }
















}
