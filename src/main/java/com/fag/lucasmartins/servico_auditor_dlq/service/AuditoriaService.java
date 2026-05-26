package com.fag.lucasmartins.servico_auditor_dlq.service;

import com.fag.lucasmartins.servico_auditor_dlq.dto.PedidoEventDTO;
import com.fag.lucasmartins.servico_auditor_dlq.model.ErrorLogEntity;
import com.fag.lucasmartins.servico_auditor_dlq.repository.ErrorLogRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuditoriaService {
    private final ErrorLogRepository repository;

    public AuditoriaService(ErrorLogRepository repository) {
        this.repository = repository;
    }

    public void processarErroDLQ(PedidoEventDTO dto, String rawPayload) {
        int totalProdutos = 0;
        if (dto.orderItems() != null) {
            totalProdutos = dto.orderItems().stream()
                    .mapToInt(item -> item.amount())
                    .sum();
        }

        String severidade;
        if (totalProdutos > 100) {
            severidade = "HIGH";
        } else if (totalProdutos >= 50) {
            severidade = "MEDIUM";
        } else {
            severidade = "LOW";
        }

        ErrorLogEntity log = new ErrorLogEntity();
        log.setErrorId(UUID.randomUUID());
        log.setQueueName("T02N_SARA_FERREIRA_DA_SILVA.fifo");
        log.setPayload(rawPayload);
        log.setTimestamp(LocalDateTime.now());
        log.setStatus("PENDING_ANALYSIS");
        log.setSeverity(severidade);

        repository.save(log);
        System.out.println(">>> [AUDITORIA] Salvo com severidade: " + severidade);
    }
}