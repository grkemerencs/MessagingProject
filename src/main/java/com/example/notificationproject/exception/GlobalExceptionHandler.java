package com.example.notificationproject.exception;

import com.example.notificationproject.Enum.Channel;
import com.example.notificationproject.Enum.DevicePlatform;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleEnumConversionException(InvalidFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Geçersiz enum değeri: " + ex.getValue()+ "\n-------Geçerli değerler------"+
                        "\nChannel:"+ Arrays.toString(Channel.values()) +
                        "\nDevice: "+ Arrays.toString(DevicePlatform.values()));
    }
} 