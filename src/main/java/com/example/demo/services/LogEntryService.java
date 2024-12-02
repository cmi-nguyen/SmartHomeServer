package com.example.demo.services;

import com.example.demo.entities.Account;
import com.example.demo.entities.LogEntry;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LogEntryService {
    // Save operation
    LogEntry saveLog(LogEntry logEntry);

    // Read operation
    List<LogEntry> fetchLogList();
}
