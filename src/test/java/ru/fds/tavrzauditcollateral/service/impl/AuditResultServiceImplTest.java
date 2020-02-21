package ru.fds.tavrzauditcollateral.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fds.tavrzauditcollateral.TestUtils;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditResult;
import ru.fds.tavrzauditcollateral.service.AuditResultService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuditResultServiceImplTest {

    @Autowired
    AuditResultService auditResultService;
    @MockBean
    RepositoryAuditResult repositoryAuditResult;

    @BeforeEach
    void setUp() {
        Pageable pageable = PageRequest.of(1, 50);
        Mockito.when(repositoryAuditResult.findAll(pageable)).thenReturn(TestUtils.getTestPageAuditResult());

        AuditResult auditResult = TestUtils.getTestAuditResult();
        Optional<AuditResult> optionalAuditResult = Optional.of(auditResult);
        Mockito.when(repositoryAuditResult.findById(auditResult.getId())).thenReturn(optionalAuditResult);
        Mockito.when(repositoryAuditResult.save(auditResult)).thenReturn(auditResult);
    }

    @Test
    void getAllAuditResults() {
        Page<AuditResult> auditResults = auditResultService.getAllAuditResults(PageRequest.of(1, 50));
        assertEquals(2, auditResults.getContent().size());
    }

    @Test
    void setAuditStatus() {
        AuditResult auditResult = auditResultService.setAuditStatus(TestUtils.getTestAuditResult().getId(), AuditStatus.IGNORE);
        assertEquals(AuditStatus.IGNORE, auditResult.getAuditStatus());
    }
}