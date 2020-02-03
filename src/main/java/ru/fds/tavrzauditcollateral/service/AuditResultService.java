package ru.fds.tavrzauditcollateral.service;

import org.springframework.data.domain.Pageable;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;

import java.util.Collection;

public interface AuditResultService {
    void doAuditInAllObjects();
    Collection<AuditResult> getAllAuditResults(Pageable pageable);
}
