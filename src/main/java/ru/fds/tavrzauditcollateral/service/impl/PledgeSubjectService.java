package ru.fds.tavrzauditcollateral.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;

import java.util.Collection;

@Slf4j
@Service
@Qualifier("pledgeSubjectService")
public class PledgeSubjectService implements ObjectAuditService {
    @Override
    @Transactional
    public void executeAuditAboutNewObject(Long id) {

    }

    @Override
    @Transactional
    public void executeAuditAboutExistObject(Long id) {

    }

    @Override
    @Transactional
    public void executeAuditAboutAllObjects() {

    }

    @Override
    public Collection<AuditResult> getAuditResultsAboutObject(Long id) {
        return null;
    }

    @Override
    public Collection<AuditResult> getAuditResultsAboutObject(Long id, AuditStatus auditStatus) {
        return null;
    }
}
