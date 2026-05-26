package com.fag.lucasmartins.servico_auditor_dlq.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoEventDTO(
    String zipCode,
    Long customerId,
    List<ItemEventDTO> orderItems,
    String origin,
    LocalDateTime occurredAt
) {}