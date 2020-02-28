package ru.fds.tavrzauditcollateral.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;
import ru.fds.tavrzauditcollateral.exception.NotFoundException;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditResult;
import ru.fds.tavrzauditcollateral.service.AuditResultService;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;

import java.util.List;

@Slf4j
@Service
public class AuditResultServiceImpl implements AuditResultService {

    private final ObjectAuditService loanAgreementService;
    private final ObjectAuditService pledgeAgreementService;
    private final ObjectAuditService pledgeSubjectService;
    private final RepositoryAuditResult repositoryAuditResult;

    public AuditResultServiceImpl(@Qualifier("loanAgreementService") ObjectAuditService loanAgreementService,
                                  @Qualifier("pledgeAgreementService") ObjectAuditService pledgeAgreementService,
                                  @Qualifier("pledgeSubjectService") ObjectAuditService pledgeSubjectService,
                                  RepositoryAuditResult repositoryAuditResult) {
        this.loanAgreementService = loanAgreementService;
        this.pledgeAgreementService = pledgeAgreementService;
        this.pledgeSubjectService = pledgeSubjectService;
        this.repositoryAuditResult = repositoryAuditResult;
    }


    @Override
    public void executeAuditInAllObjects() {
        log.info("start executeAuditInAllObjects");
        loanAgreementService.executeAuditAboutAllObjects();
        pledgeAgreementService.executeAuditAboutAllObjects();
        pledgeSubjectService.executeAuditAboutAllObjects();
        log.info("end executeAuditInAllObjects");
    }

    @Override
    public List<AuditResult> getAllAuditResults(Pageable pageable) {
        return repositoryAuditResult.findAll(pageable).getContent();
    }

    @Override
    @Transactional
    public AuditResult setAuditStatus(String auditResultId, AuditStatus auditStatus) {
        log.info("setAuditStatus for object. id: {}, status: {}", auditResultId, auditStatus);
        return repositoryAuditResult.findById(auditResultId).map(auditResult -> {
            auditResult.setAuditStatus(auditStatus);
            return repositoryAuditResult.save(auditResult);
        }).orElseThrow(() -> new NotFoundException("Audit result not found"));
    }
}
