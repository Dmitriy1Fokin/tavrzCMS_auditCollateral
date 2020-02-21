package ru.fds.tavrzauditcollateral.repository.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditPledgeSubject;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
class RepositoryAuditPledgeSubjectImplTest {

    @Autowired
    RepositoryAuditPledgeSubject repositoryAuditPledgeSubject;

    @Before
    public void setUp() {
    }

    @Test
    void getPledgeSubjectWithLowLiquidityAndNotZeroSs() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectWithLowLiquidityAndNotZeroSs();
        assertEquals(5707, auditResults.size());
    }

    @Test
    void isHaveLowLiquidityAndNotZeroSS() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isHaveLowLiquidityAndNotZeroSS(1L);
        Optional<ObjectAudit> objectAuditExist = repositoryAuditPledgeSubject.isHaveLowLiquidityAndNotZeroSS(2L);

        assertFalse(objectAuditNotExist.isPresent());
        assertTrue(objectAuditExist.isPresent());
    }

    @Test
    void getPledgeSubjectsWithZeroZsDz() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsWithZeroZsDz();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isHaveZeroZsDz() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isHaveZeroZsDz(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeSubjectsWithZeroZsZz() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsWithZeroZsZz();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isHaveZeroZsZz() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isHaveZeroZsZz(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeSubjectsWithZeroRsDz() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsWithZeroRsDz();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isHaveZeroRsDz() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isHaveZeroRsDz(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeSubjectsWithZeroRsZz() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsWithZeroRsZz();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isHaveZeroRsZz() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isHaveZeroRsZz(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeSubjectsWithZeroSs() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsWithZeroSs();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isHaveZeroSs() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isHaveZeroSs(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeSubjectsWithMonitoringOverdue() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsWithMonitoringOverdue();
        assertEquals(16299, auditResults.size());
    }

    @Test
    void isMonitoringOverdue() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isMonitoringOverdue(1L);
        Optional<ObjectAudit> objectAuditExist = repositoryAuditPledgeSubject.isMonitoringOverdue(2L);

        assertFalse(objectAuditNotExist.isPresent());
        assertTrue(objectAuditExist.isPresent());
    }

    @Test
    void getPledgeSubjectsWithConclusionOverdue() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsWithConclusionOverdue();
        assertEquals(16214, auditResults.size());
    }

    @Test
    void isConclusionOverdue() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isConclusionOverdue(6L);
        Optional<ObjectAudit> objectAuditExist = repositoryAuditPledgeSubject.isConclusionOverdue(1L);

        assertFalse(objectAuditNotExist.isPresent());
        assertTrue(objectAuditExist.isPresent());
    }

    @Test
    void getPledgeSubjectsLosing() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsLosing();
        assertEquals(7681, auditResults.size());
    }

    @Test
    void isLosing() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isLosing(1L);
        Optional<ObjectAudit> objectAuditExist = repositoryAuditPledgeSubject.isLosing(3L);

        assertFalse(objectAuditNotExist.isPresent());
        assertTrue(objectAuditExist.isPresent());
    }

    @Test
    void getPledgeSubjectWithoutInsurance() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectWithoutInsurance();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isHaveNotInsurance() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isHaveNotInsurance(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeSubjectWithoutOurEncumbrance() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectWithoutOurEncumbrance();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isHaveNotOurEncumbrance() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isHaveNotOurEncumbrance(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeSubjectsWithOtherEncumbrance() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsWithOtherEncumbrance();
        assertTrue(auditResults.isEmpty());
    }

    @Test
    void isHaveOtherEncumbrance() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isHaveOtherEncumbrance(1L);
        assertFalse(objectAuditNotExist.isPresent());
    }

    @Test
    void getPledgeSubjectsWithEncumbranceOverdue() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsWithEncumbranceOverdue();
        assertEquals(593, auditResults.size());
    }

    @Test
    void isEncumbranceOverdue() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isEncumbranceOverdue(1L);
        Optional<ObjectAudit> objectAuditExist = repositoryAuditPledgeSubject.isEncumbranceOverdue(23L);

        assertFalse(objectAuditNotExist.isPresent());
        assertTrue(objectAuditExist.isPresent());
    }

    @Test
    void getPledgeSubjectsWithInsuranceOverdue() {
        Collection<ObjectAudit> auditResults = repositoryAuditPledgeSubject.getPledgeSubjectsWithInsuranceOverdue();
        assertEquals(306, auditResults.size());
    }

    @Test
    void isInsuranceOverdue() {
        Optional<ObjectAudit> objectAuditNotExist = repositoryAuditPledgeSubject.isInsuranceOverdue(1L);
        Optional<ObjectAudit> objectAuditExist = repositoryAuditPledgeSubject.isInsuranceOverdue(104L);

        assertFalse(objectAuditNotExist.isPresent());
        assertTrue(objectAuditExist.isPresent());
    }
}