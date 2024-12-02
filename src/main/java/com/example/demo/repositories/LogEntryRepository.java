package com.example.demo.repositories;

import com.example.demo.entities.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Integer> {
}
