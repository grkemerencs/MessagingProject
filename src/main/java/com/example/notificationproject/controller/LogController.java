package com.example.notificationproject.controller;

import com.example.notificationproject.Model.entity.Log;
import com.example.notificationproject.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/last/{count}")
    public List<Log> getLastNLogEntities(@PathVariable int count) {
        return logService.getLastNLogEntities(count);
    }

    @GetMapping("/all")
    public List<Log> getAllLogEntities() {
        return logService.getAllLogEntities();
    }
} 