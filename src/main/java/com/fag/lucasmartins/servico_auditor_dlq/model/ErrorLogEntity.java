package com.fag.lucasmartins.servico_auditor_dlq.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_AUDITORIA_DLQ")
@Getter
@Setter
public class ErrorLogEntity {
    @Id
    private UUID errorId;
    @Column(nullable = false)
    private String queueName;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;
    @Column(nullable = false)
    private LocalDateTime timestamp;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String severity;
}