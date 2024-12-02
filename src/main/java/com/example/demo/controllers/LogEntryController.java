package com.example.demo.controllers;


import com.example.demo.entities.Account;
import com.example.demo.entities.LogEntry;
import com.example.demo.services.LogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class LogEntryController {
    @Autowired
    private LogEntryService logEntryService;

    // Get  mapping
    @CrossOrigin
    @GetMapping("/logs")
    public List<LogEntry> getLogList(){return logEntryService.fetchLogList();}

    @CrossOrigin
    @PostMapping("/logs")
    public LogEntry postLog(@RequestBody LogEntry logEntry) throws NoSuchAlgorithmException {
        return logEntryService.saveLog(logEntry);
    }
}
