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
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditPledgeAgreement;
import ru.fds.tavrzauditcollateral.utils.DateUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
class RepositoryAuditPledgeAgreementImplTest {

    @Autowired
    RepositoryAuditPledgeAgreement repositoryAuditPledgeAgreement;
    @MockBean
    DateUtils dateUtils;

    @BeforeEach
    public void setUp() {
        Mockito.when(dateUtils.getNow()).thenReturn(LocalDate.of(2020, 2, 21));
    }

    @Test
    void getPledgeAgreementWithDateClosedOverdue() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeAgreement.getPledgeAgreementWithDateClosedOverdue();
        assertEquals(52, auditResults.size());
    }

    @Test
    void isDateClosedOverDue() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeAgreement.isDateClosedOverDue(1L);
        Optional<ObjectAudit> objectAuditExist = repositoryAuditPledgeAgreement.isDateClosedOverDue(3L);

        assertFalse(objectAuditNotExist.isPresent());
        assertTrue(objectAuditExist.isPresent());
    }

    @Test
    void getPledgeAgreementsWithoutLoanAgreement() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeAgreement.getPledgeAgreementsWithoutLoanAgreement();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isHaveNotLoanAgreements() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeAgreement.isHaveNotLoanAgreements(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeAgreementsWithZeroZsDZ() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeAgreement.getPledgeAgreementsWithZeroZsDZ();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isZeroZsDz() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeAgreement.isZeroZsDz(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeAgreementsWithZeroZsZZ() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeAgreement.getPledgeAgreementsWithZeroZsZZ();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isZeroZsZZ() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeAgreement.isZeroZsZZ(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeAgreementsWithoutPledgeSubjects() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeAgreement.getPledgeAgreementsWithoutPledgeSubjects();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isHaveNotPledgeSubjects() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeAgreement.isHaveNotPledgeSubjects(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }
}