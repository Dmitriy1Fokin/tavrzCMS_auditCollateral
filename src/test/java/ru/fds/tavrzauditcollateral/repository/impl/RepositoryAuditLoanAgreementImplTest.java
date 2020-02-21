package ru.fds.tavrzauditcollateral.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditLoanAgreement;
import ru.fds.tavrzauditcollateral.utils.DateUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class RepositoryAuditLoanAgreementImplTest {

    @Autowired
    private RepositoryAuditLoanAgreement repositoryAuditLoanAgreement;
    @MockBean
    DateUtils dateUtils;

    @BeforeEach
    public void setUp(){
        Mockito.when(dateUtils.getNow()).thenReturn(LocalDate.of(2020, 2, 21));
    }

    @Test
    void getLoanAgreementWithDateClosedOverdue() {
        Collection<ObjectAudit> auditResults = repositoryAuditLoanAgreement.getLoanAgreementWithDateClosedOverdue();
        assertEquals(22, auditResults.size());
    }

    @Test
    void isDateClosedOverDue() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditLoanAgreement.isDateClosedOverDue(1L);
        Optional<ObjectAudit> objectAuditExist = repositoryAuditLoanAgreement.isDateClosedOverDue(2L);

        assertFalse(objectAuditNotExist.isPresent());
        assertTrue(objectAuditExist.isPresent());
    }

    @Test
    void getLoanAgreementWithLowCollateralValue() {
        Collection<ObjectAudit> auditResults = repositoryAuditLoanAgreement.getLoanAgreementWithLowCollateralValue();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isLowCollateralSum() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditLoanAgreement.isLowCollateralSum(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getLoanAgreementWithoutPledge() {
        Collection<ObjectAudit> auditResults = repositoryAuditLoanAgreement.getLoanAgreementWithoutPledge();
        assertEquals(134, auditResults.size());
    }

    @Test
    void isHaveNotPledgeAgreements() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditLoanAgreement.isHaveNotPledgeAgreements(1L);
        Optional<ObjectAudit> objectAuditExist = repositoryAuditLoanAgreement.isHaveNotPledgeAgreements(271L);

        assertFalse(objectAuditNotExist.isPresent());
        assertTrue(objectAuditExist.isPresent());
    }
}