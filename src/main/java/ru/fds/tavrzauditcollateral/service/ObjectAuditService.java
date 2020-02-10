package ru.fds.tavrzauditcollateral.service;

import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;

import java.util.Collection;

public interface ObjectAuditService {
    void executeAuditAboutNewObject(Long id);

    void executeAuditAboutExistObject(Long id);

    void executeAuditAboutAllObjects();

    Collection<AuditResult> getAuditResultsAboutObject(Long id);

    Collection<AuditResult> getAuditResultsAboutObject(Long id, AuditStatus auditStatus);
}
