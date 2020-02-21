package ru.fds.tavrzauditcollateral.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditResult;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoanAgreementServiceImplTest {

    @Autowired
    @Qualifier("loanAgreementService")
    ObjectAuditService loanAgreementService;

    @MockBean
    RepositoryAuditResult repositoryAuditResult;

    @BeforeEach
    void setUp() {
        AuditResult auditResult = ServiceTestUtils.getTestAuditResult();
        Collection<AuditResult> auditResults = ServiceTestUtils.getTestListAuditResult();
        Mockito.when(repositoryAuditResult.getExistAuditResults(TypeOfObject.LOAN_AGREEMENT, auditResult.getObjectId()))
                .thenReturn(auditResults);
        Mockito.when(repositoryAuditResult.getExistAuditResults(TypeOfObject.LOAN_AGREEMENT, auditResult.getObjectId(), AuditStatus.ACTUAL))
                .thenReturn(auditResults);
    }

    @Test
    void getAuditResultsAboutObject() {
        Collection<AuditResult> auditResults = loanAgreementService.getAuditResultsAboutObject(ServiceTestUtils.getTestAuditResult().getObjectId());
        assertEquals(2, auditResults.size());
    }

    @Test
    void testGetAuditResultsAboutObject() {
        Collection<AuditResult> auditResults = loanAgreementService.getAuditResultsAboutObject(ServiceTestUtils.getTestAuditResult().getObjectId(), AuditStatus.ACTUAL);
        assertEquals(2, auditResults.size());
    }
}