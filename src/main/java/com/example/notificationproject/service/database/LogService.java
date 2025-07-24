package com.example.notificationproject.service.database;

import com.example.notificationproject.Model.entity.Log;
import com.example.notificationproject.repository.LogRepository;
import com.google.firebase.database.DatabaseException;
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

    public String clearAllLogs() {
        try{
            logRepository.deleteAll();
            return "all logs deleted";
        } catch(Exception ex){
            throw new DatabaseException("Error in clearing all logs.");
        }
    }

    public List<Log> getLastNLogEntities(int n) {
        return logRepository.findAll(PageRequest.of(0, n, Sort.by(Sort.Direction.DESC, "timestamp"))).getContent();
    }

    public List<Log> getAllLogEntities() {
        return logRepository.findAll();
    }
} 