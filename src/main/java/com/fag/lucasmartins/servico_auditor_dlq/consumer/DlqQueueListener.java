package com.fag.lucasmartins.servico_auditor_dlq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fag.lucasmartins.servico_auditor_dlq.dto.PedidoEventDTO;
import com.fag.lucasmartins.servico_auditor_dlq.service.AuditoriaService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class DlqQueueListener {
    private final AuditoriaService auditoriaService;
    private final ObjectMapper objectMapper;

    public DlqQueueListener(AuditoriaService auditoriaService, ObjectMapper objectMapper) {
        this.auditoriaService = auditoriaService;
        this.objectMapper = objectMapper;
    }

    @SqsListener("T02N_SARA_FERREIRA_DA_SILVA-DLQ.fifo")
    public void listen(String rawPayload) {
        try {
            System.out.println(">>> [DLQ] Mensagem recebida: " + rawPayload);
            PedidoEventDTO dto = objectMapper.readValue(rawPayload, PedidoEventDTO.class);
            auditoriaService.processarErroDLQ(dto, rawPayload);
        } catch (Exception e) {
            System.err.println("Erro ao processar auditoria: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}