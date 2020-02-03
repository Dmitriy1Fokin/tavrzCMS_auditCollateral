package ru.fds.tavrzauditcollateral.repository;

import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;

import java.util.Collection;
import java.util.Optional;

public interface RepositoryAuditLoanAgreement {
    Collection<ObjectAudit> getLoanAgreementWithDateClosedOverdue();
    Optional<ObjectAudit> isDateClosedOverDue(Long loanAgreementId);
    Collection<ObjectAudit> getLoanAgreementWithLowCollateralValue();
    Optional<ObjectAudit> isLowCollateralSum(Long loanAgreementId);
    Collection<ObjectAudit> getLoanAgreementWithoutPledge();
    Optional<ObjectAudit> isHaveNotPledgeAgreements(Long loanAgreementId);
}
