package com.example.notificationproject.exception;

public abstract class MessageException extends BaseNotificationServiceException{

    public MessageException(int errorCode, String failureMessage) {
        super(errorCode, failureMessage);
    }

    public static class EmailMessagingException extends MessageException {

        public EmailMessagingException(String failureMessage) {
            super(ErrorCodes.EmailMessagingException, failureMessage);
        }
    }
    public static class TelegramMessagingException extends MessageException {

        public TelegramMessagingException(String failureMessage) {
            super(ErrorCodes.TelegramMessagingException, failureMessage);
        }
    }
    public static class FirebaseMessagingException extends MessageException {

        public FirebaseMessagingException(String failureMessage) {
            super(ErrorCodes.FirebaseMessagingException, failureMessage);
        }
    }
}
