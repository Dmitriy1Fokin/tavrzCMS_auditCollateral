package ru.fds.tavrzauditcollateral.service;

import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;

import java.util.Collection;

public interface ObjectAuditService {
    void doAuditAboutNewObject(Long id);

    void doAuditAboutExitObject(Long id);

    void doAuditAboutAllObjects();

    Collection<AuditResult> getAuditResultsAboutObject(Long id);

    Collection<AuditResult> getAuditResultsAboutObject(Long id, AuditStatus auditStatus);
}
