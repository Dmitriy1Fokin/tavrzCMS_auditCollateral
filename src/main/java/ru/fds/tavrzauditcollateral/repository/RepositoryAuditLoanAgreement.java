package ru.fds.tavrzauditcollateral.repository;

import ru.fds.tavrzauditcollateral.domain.sql.LoanAgreementAuditDateClosed;
import ru.fds.tavrzauditcollateral.domain.sql.LoanAgreementAuditLowCollateralValue;
import ru.fds.tavrzauditcollateral.domain.sql.LoanAgreementWithoutPA;

import java.util.Collection;
import java.util.Optional;

public interface RepositoryAuditLoanAgreement {
    Collection<LoanAgreementAuditDateClosed> getLoanAgreementWithDateClosedOverdue();

    Optional<LoanAgreementAuditDateClosed> isDateClosedOverDue(Long loanAgreementId);

    Collection<LoanAgreementAuditLowCollateralValue> getLoanAgreementWithLowCollateralValue();

    Optional<LoanAgreementAuditLowCollateralValue> isLowCollateralSum(Long loanAgreementId);

    Collection<LoanAgreementWithoutPA> getLoanAgreementWithoutPledge();

    Optional<LoanAgreementWithoutPA> isNotHavePledgeAgreements(Long loanAgreementId);
}
