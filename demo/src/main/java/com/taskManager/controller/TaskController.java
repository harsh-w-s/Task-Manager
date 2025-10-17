package com.taskManager.controller;

import com.taskManager.model.Task;
import com.taskManager.service.TasksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TasksService tasksService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<Page<Task>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(tasksService.getAll(page, size), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Task> addTask(@Valid @RequestBody Task task) {
        ResponseEntity<Task> saved = tasksService.addTask(task);
        if(saved.getStatusCode().isSameCodeAs(HttpStatus.CREATED)) {
            return new ResponseEntity<>(saved.getBody(), saved.getStatusCode());
        }
        return new ResponseEntity<>(saved.getStatusCode());
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody Task task) {
        return tasksService.updateTask(task, id);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return tasksService.deleteById(id);
    }
}
