package com.example.notificationproject.Model.entity;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "request_logs")
public class Log {
    @Id
    private String id;
    private String ip;
    private String uri;
    private String method;
    private LocalDateTime timestamp;
    private String response;


    public Log(String ip, String uri, String method, LocalDateTime timestamp, String response) {
        this.ip = ip;
        this.uri = uri;
        this.method = method;
        this.timestamp = timestamp;
        this.response = response;
    }

    public Log(HttpServletRequest request, String response) {
        this.ip = request.getRemoteAddr();
        this.uri = request.getRequestURI();
        this.method = request.getMethod();
        this.timestamp = LocalDateTime.now();
        this.response = response;
    }
} 