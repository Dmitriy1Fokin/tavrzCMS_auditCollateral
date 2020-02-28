package ru.fds.tavrzauditcollateral.service;

import org.springframework.data.domain.Pageable;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;

import java.util.List;

public interface AuditResultService {
    void executeAuditInAllObjects();
    List<AuditResult> getAllAuditResults(Pageable pageable);
    AuditResult setAuditStatus(String auditResultId, AuditStatus auditStatus);

}
