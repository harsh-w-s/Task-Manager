package com.taskManager.repository;

import com.taskManager.model.AuditLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<AuditLogs, Long> {}
