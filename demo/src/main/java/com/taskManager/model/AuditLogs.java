package com.taskManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit_logs")
public class AuditLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @Column(nullable = false)
    private Instant createdAt;

    @NotBlank
    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private Long taskId;

    @Column(name = "updated_title", columnDefinition = "TEXT")
    private String updatedTitle;

    @Column(name = "updated_description", columnDefinition = "TEXT")
    private String updatedDescription;
}
