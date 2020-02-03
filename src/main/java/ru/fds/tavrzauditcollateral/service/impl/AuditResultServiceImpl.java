package ru.fds.tavrzauditcollateral.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;
import ru.fds.tavrzauditcollateral.service.AuditResultService;

import java.util.Collection;

@Slf4j
@Service
public class AuditResultServiceImpl implements AuditResultService {
    @Override
    public void doAuditInAllObjects() {

    }

    @Override
    public Collection<AuditResult> getAllAuditResults(Pageable pageable) {
        return null;
    }
}
