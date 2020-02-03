package ru.fds.tavrzauditcollateral.repository;

import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;

import java.util.Collection;
import java.util.Optional;

public interface RepositoryAuditPledgeSubject {
    Collection<ObjectAudit> getPledgeSubjectWithLowLiquidityAndNotZeroSs();
    Optional<ObjectAudit> isHaveLowLiquidityAndNotZeroSS(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsWithZeroZsDz();
    Optional<ObjectAudit> isHaveZeroZsDz(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsWithZeroZzZz();
    Optional<ObjectAudit> isHaveZeroZsZz(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsWithZeroRsDz();
    Optional<ObjectAudit> isHaveZeroRsDz(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsWithZeroRsZz();
    Optional<ObjectAudit> isHaveZeroRsZz(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsWithZeroSs();
    Optional<ObjectAudit> isHaveZeroSs(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsWithMonitoringOverdue();
    Optional<ObjectAudit> isMonitoringOverdue(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsWithConclusionOverdue();
    Optional<ObjectAudit> isConclusionOverdue(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsLosing();
    Optional<ObjectAudit> isLosing(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectWithoutInsurance();
    Optional<ObjectAudit> isHaveNotInsurance(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectWithoutOurEncumbrance();
    Optional<ObjectAudit> isHaveNotOurEncumbrance(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsWithOtherEncumbrance();
    Optional<ObjectAudit> isHaveOtherEncumbrance(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsWithEncumbranceOverdue();
    Optional<ObjectAudit> isEncumbranceOverdue(Long pledgeSubjectId);
    Collection<ObjectAudit> getPledgeSubjectsWithInsuranceOverdue();
    Optional<ObjectAudit> isInsuranceOverdue(Long pledgeSubjectId);
}
