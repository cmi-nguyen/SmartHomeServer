package com.example.demo.services;

import com.example.demo.entities.LogEntry;
import com.example.demo.repositories.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogEntryServiceImpl implements  LogEntryService{
    @Autowired
    private LogEntryRepository logEntryRepository;
    @Override
    public LogEntry saveLog(LogEntry logEntry) {
        return logEntryRepository.save(logEntry);
    }

    @Override
    public List<LogEntry> fetchLogList() {
        return logEntryRepository.findAll();
    }
}
