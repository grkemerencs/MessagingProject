package com.example.notificationproject.controller;

import com.example.notificationproject.Model.entity.Log;
import com.example.notificationproject.Model.dto.respond.ApiRespondDTO;
import com.example.notificationproject.service.database.LogService;
import com.google.protobuf.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/logs")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/{count}")
    public ResponseEntity<ApiRespondDTO<List<Log>>> getLastNLogEntities(@PathVariable int count) {
        return ResponseEntity.ok(new ApiRespondDTO<>(logService.getLastNLogEntities(count)));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiRespondDTO<List<Log>>> getAllLogEntities() {
        return ResponseEntity.ok(new ApiRespondDTO<>(logService.getAllLogEntities()));
    }

    @DeleteMapping("/all")
    public ResponseEntity<ApiRespondDTO<String>> clearAllLogEntities(){
        return ResponseEntity.ok(new ApiRespondDTO<String>(logService.clearAllLogs()));
    }
} 