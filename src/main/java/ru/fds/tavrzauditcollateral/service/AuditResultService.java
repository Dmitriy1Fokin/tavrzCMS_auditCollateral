package ru.fds.tavrzauditcollateral.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;

public interface AuditResultService {
    void executeAuditInAllObjects();
    Page<AuditResult> getAllAuditResults(Pageable pageable);
    AuditResult setAuditStatus(String auditResultId, AuditStatus auditStatus);

}
