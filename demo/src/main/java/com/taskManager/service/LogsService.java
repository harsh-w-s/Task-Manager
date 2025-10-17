package com.taskManager.service;

import com.taskManager.model.AuditLogs;
import com.taskManager.model.Task;
import com.taskManager.repository.LogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LogsService {

    @Autowired
    private LogsRepository logsRepo;

    //GET ALL LOGS
    public List<AuditLogs> getAll() {
        return logsRepo.findAll();
    }

    //ADD A NEW TASK
    public void addLog(String title, String description, String action, Long id) {
        if ((title == null || title.isBlank()) && (description == null || description.isBlank())) {
            return; //NO CHANGES, SO NO LOGGING
        }
        AuditLogs newLog = new AuditLogs();
        newLog.setCreatedAt(Instant.now());
        newLog.setAction(action);
        newLog.setTaskId(id);
        newLog.setUpdatedTitle(title);
        newLog.setUpdatedDescription(description);
        logsRepo.save(newLog);
  }
}
