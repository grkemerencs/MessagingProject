package com.example.notificationproject.aspect;

import com.example.notificationproject.Model.entity.Log;
import com.example.notificationproject.service.database.LogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {
    @Autowired
    private LogService logService;

    @Autowired
    private ObjectMapper objectMapper;

    @Around("within(com.example.notificationproject.controller.NotificationController)")
    public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        LocalDateTime timestamp = LocalDateTime.now();

        Object result = joinPoint.proceed();
        String responseJson = null;
        try {
            responseJson = objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            responseJson = "Yanıt Stringe Dönüştürülemedi";
        }
        Log log = new Log(ip, uri, method, timestamp, responseJson);
        logService.saveLogAsync(log);
        return result;
    }
} 