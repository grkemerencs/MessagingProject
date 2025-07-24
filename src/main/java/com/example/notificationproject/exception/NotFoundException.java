package com.example.notificationproject.exception;

import com.example.notificationproject.mapper.UserDeviceMapper;

public abstract class NotFoundException extends BaseNotificationServiceException {


    public NotFoundException(String failureMessage) {
        super(ErrorCodes.NotFoundException,failureMessage);
    }

    public static class UserDeviceNotFound extends NotFoundException {
        public UserDeviceNotFound(String deviceId) {
            super("Device can't found with the ıd: "+deviceId);
        }
    }
    public static class MessageTemplateNotFound extends NotFoundException {
        public MessageTemplateNotFound(String messageTemplateName) {
            super("Message template can't found with the name: "+messageTemplateName);
        }
    }
    public static class EmailAddressNotFound extends NotFoundException {
        public EmailAddressNotFound(String emailId) {
            super("Email Address can't found with the ıd: "+emailId);
        }
    }
    public static class UserTelegramAccountNotFound extends NotFoundException {
        public UserTelegramAccountNotFound(String userTelegramAccountId) {
            super("User Telegram Account can't found with the ıd: "+userTelegramAccountId);
        }
    }

}
