package ru.fds.tavrzauditcollateral.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fds.tavrzauditcollateral.TestUtils;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditResult;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;

import java.util.Collection;

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
        AuditResult auditResult = TestUtils.getTestAuditResult();
        Collection<AuditResult> auditResults = TestUtils.getTestListAuditResult();
        Mockito.when(repositoryAuditResult.getExistAuditResults(TypeOfObject.LOAN_AGREEMENT, auditResult.getObjectId()))
                .thenReturn(auditResults);
        Mockito.when(repositoryAuditResult.getExistAuditResults(TypeOfObject.LOAN_AGREEMENT, auditResult.getObjectId(), AuditStatus.ACTUAL))
                .thenReturn(auditResults);
    }

    @Test
    void getAuditResultsAboutObject() {
        Collection<AuditResult> auditResults = loanAgreementService.getAuditResultsAboutObject(TestUtils.getTestAuditResult().getObjectId());
        assertEquals(2, auditResults.size());
    }

    @Test
    void testGetAuditResultsAboutObject() {
        Collection<AuditResult> auditResults = loanAgreementService.getAuditResultsAboutObject(TestUtils.getTestAuditResult().getObjectId(), AuditStatus.ACTUAL);
        assertEquals(2, auditResults.size());
    }
}