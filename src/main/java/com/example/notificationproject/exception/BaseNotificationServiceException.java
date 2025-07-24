package com.example.notificationproject.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public abstract class BaseNotificationServiceException extends RuntimeException {
    private final HttpStatus httpStatus = HttpStatus.OK;
    private final int errorCode;

    public BaseNotificationServiceException(int errorCode,String failureMessage){
        super(failureMessage);
        this.errorCode = errorCode;
    }
}
