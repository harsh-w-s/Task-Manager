package com.taskManager.controller;

import com.taskManager.model.AuditLogs;
import com.taskManager.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogsController {

    @Autowired
    private LogsService logsService;

    //GET ALL LOGS
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<AuditLogs> getALlLogs() {
        return logsService.getAll();
    }
}
