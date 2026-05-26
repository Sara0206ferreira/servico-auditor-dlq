package com.fag.lucasmartins.servico_auditor_dlq.repository;

import com.fag.lucasmartins.servico_auditor_dlq.model.ErrorLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLogEntity, UUID> {
}