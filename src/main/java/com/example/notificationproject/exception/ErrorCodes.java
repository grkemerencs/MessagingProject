package com.example.notificationproject.exception;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodes {
    public static int FirebaseInitialization = 1001;
    public static int NotFoundException = 1002;
    public static int InvalidFormatException = 1003;
    public static int MongoDuplicateIndexException = 1004;

    public static int DataBaseException = 1005;

    public static int  FirebaseMessagingException = 2001;
    public static int  EmailMessagingException = 2002;
    public static int  TelegramMessagingException = 2003;




}
