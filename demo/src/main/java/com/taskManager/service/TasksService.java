    package com.taskManager.service;

    import com.taskManager.model.AuditLogs;
    import com.taskManager.model.Task;
    import com.taskManager.repository.LogsRepository;
    import com.taskManager.repository.TasksRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;

    import java.time.Instant;
    import java.util.Objects;
    import java.util.Optional;

    @Service
    public class TasksService {

        @Autowired
        private TasksRepository taskRepo;

        @Autowired
        private LogsService logsService;

        //GET ALL TASKS(PAGINATED)
        public Page<Task> getAll(int page, int size) {
            Pageable pageable = PageRequest.of(page, size);
            return taskRepo.findAll(pageable);
        }

        //ADD A NEW TASK
        public ResponseEntity<Task> addTask(Task task) {
            try {
                task.setCreatedAt(Instant.now());
                Task saved = taskRepo.save(task);
                logsService.addLog(task.getTitle(), task.getDescription(), "Create Task", saved.getId());
                return new ResponseEntity<>(saved, HttpStatus.CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        //UPDATE A TASK
        public ResponseEntity<Task> updateTask(Task task, Long id) {
            Task old = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("task not found"));

            //CHANGED FIELDS
            String updatedTitle = null;
            String updatedDescription = null;

            //LOGGING
            updatedTitle= Objects.equals(task.getTitle(), old.getTitle()) ? null : task.getTitle();
            updatedDescription = Objects.equals(task.getDescription(), old.getDescription()) ? null : task.getDescription();

            old.setTitle(task.getTitle());
            old.setDescription(task.getDescription());
            taskRepo.save(old);
            logsService.addLog(updatedTitle, updatedDescription, "Update Task", old.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        //DELETE A TASK
        public ResponseEntity<?> deleteById(Long id) {
            Task present = taskRepo.findById(id).orElse(null);
            if (present != null) {
                taskRepo.deleteById(id);
                logsService.addLog("", "", "Delete Task", id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
