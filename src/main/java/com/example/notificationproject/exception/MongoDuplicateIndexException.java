package com.example.notificationproject.exception;

public class MongoDuplicateIndexException extends BaseNotificationServiceException{
    public MongoDuplicateIndexException(String failureMessage) {
        super(ErrorCodes.MongoDuplicateIndexException, failureMessage);
    }
}
