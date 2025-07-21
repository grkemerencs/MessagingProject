package com.example.notificationproject.service;

import com.example.notificationproject.entity.Log;
import com.example.notificationproject.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    @Async
    public void saveLogAsync(Log log) {
        logRepository.save(log);
    }

    public List<Log> getLastNLogs(int n) {
        return logRepository.findAll(PageRequest.of(0, n, Sort.by(Sort.Direction.DESC, "timestamp"))).getContent();
    }

    public List<Log> getAllLogs() {
        return logRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
    }
} 