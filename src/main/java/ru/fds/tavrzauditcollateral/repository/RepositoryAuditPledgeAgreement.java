package ru.fds.tavrzauditcollateral.repository;

import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;

import java.util.Collection;
import java.util.Optional;

public interface RepositoryAuditPledgeAgreement {
    Collection<ObjectAudit> getPledgeAgreementWithDateClosedOverdue();
    Optional<ObjectAudit> isDateClosedOverDue(Long pledgeAgreementId);
    Collection<ObjectAudit> getPledgeAgreementsWithoutLoanAgreement();
    Optional<ObjectAudit> isHaveNotLoanAgreements(Long pledgeAgreementId);
    Collection<ObjectAudit> getPledgeAgreementsWithZeroZsDZ();
    Optional<ObjectAudit> isZeroZsDz(Long pledgeAgreementId);
    Collection<ObjectAudit> getPledgeAgreementsWithZeroZsZZ();
    Optional<ObjectAudit> isZeroZsZZ(Long pledgeAgreementId);
    Collection<ObjectAudit> getPledgeAgreementsWithoutPerv();
    Optional<ObjectAudit> isHaveNotPerv(Long pledgeAgreementId);
    Collection<ObjectAudit> getPledgeAgreementsWithoutPledgeSubjects();
    Optional<ObjectAudit> isHaveNotPledgeSubjects(Long pledgeAgreementId);
}
