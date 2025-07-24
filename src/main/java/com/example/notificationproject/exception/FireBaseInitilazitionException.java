package com.example.notificationproject.exception;

public class FireBaseInitilazitionException extends BaseNotificationServiceException{

    public FireBaseInitilazitionException(String failureMessage) {
        super(ErrorCodes.FirebaseInitialization, failureMessage);
    }
}
