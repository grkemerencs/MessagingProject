package com.example.notificationproject.exception;

public class DataBaseException extends BaseNotificationServiceException{

    public DataBaseException(String failureMessage) {
        super(ErrorCodes.DataBaseException, failureMessage);
    }
}
