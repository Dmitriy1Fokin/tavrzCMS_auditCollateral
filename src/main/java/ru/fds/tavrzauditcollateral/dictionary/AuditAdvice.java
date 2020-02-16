package ru.fds.tavrzauditcollateral.dictionary;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public final class AuditAdvice {
    @Value("#{'${audit_advice.loan_agreement.date_closed}'.split(';')}")
    private List<String> loanAgreementDateClosed;
    @Value("#{'${audit_advice.loan_agreement.amount}'.split(';')}")
    private List<String> loanAgreementAmount;
    @Value("#{'${audit_advice.loan_agreement.not_exist_pa}'.split(';')}")
    private List<String> loanAgreementNotExistPA;

    @Value("#{'${audit_advice.pledge_agreement.date_closed}'.split(';')}")
    private List<String> pledgeAgreementDateClosed;
    @Value("#{'${audit_advice.pledge_agreement.not_exist_la}'.split(';')}")
    private List<String> pledgeAgreementNotExistLA;
    @Value("#{'${audit_advice.pledge_agreement.zero_zs_dz}'.split(';')}")
    private List<String> pledgeAgreementZeroZzDZ;
    @Value("#{'${audit_advice.pledge_agreement.zero_zs_zz}'.split(';')}")
    private List<String> pledgeAgreementZeroZsZz;
    @Value("#{'${audit_advice.pledge_agreement.not_exist_ps}'.split(';')}")
    private List<String> pledgeAgreementNotExistPS;

    @Value("#{'${audit_advice.pledge_subject.low_liquidity_not_zero_ss}'.split(';')}")
    private List<String> pledgeSubjectLowLiquidityNotZeroSS;
    @Value("#{'${audit_advice.pledge_subject.zero_zs_dz}'.split(';')}")
    private List<String> pledgeSubjectZeroZsDz;
    @Value("#{'${audit_advice.pledge_subject.zero_zs_zz}'.split(';')}")
    private List<String> pledgeSubjectZeroZsZz;
    @Value("#{'${audit_advice.pledge_subject.zero_rs_dz}'.split(';')}")
    private List<String> pledgeSubjectZeroRsDz;
    @Value("#{'${audit_advice.pledge_subject.zero_rs_zz}'.split(';')}")
    private List<String> pledgeSubjectZeroRsZz;
    @Value("#{'${audit_advice.pledge_subject.zero_ss}'.split(';')}")
    private List<String> pledgeSubjectZeroSs;
    @Value("#{'${audit_advice.pledge_subject.monitoring_overdue}'.split(';')}")
    private List<String> pledgeSubjectMonitoringOverdue;
    @Value("#{'${audit_advice.pledge_subject.conclusion_overdue}'.split(';')}")
    private List<String> pledgeSubjectConclusionOverdue;
    @Value("#{'${audit_advice.pledge_subject.losing}'.split(';')}")
    private List<String> pledgeSubjectLosing;
    @Value("#{'${audit_advice.pledge_subject.not_exist_insurance}'.split(';')}")
    private List<String> pledgeSubjectNotExistInsurance;
    @Value("#{'${audit_advice.pledge_subject.not_exist_encumbrance}'.split(';')}")
    private List<String> pledgeSubjectNotExistEncumbrance;
    @Value("#{'${audit_advice.pledge_subject.exist_other_encumbrance}'.split(';')}")
    private List<String> pledgeSubjectExistOtherEncumbrance;
    @Value("#{'${audit_advice.pledge_subject.insurance_overdue}'.split(';')}")
    private List<String> pledgeSubjectInsuranceOverdue;
    @Value("#{'${audit_advice.pledge_subject.encumbrance_overdue}'.split(';')}")
    private List<String> pledgeSubjectEncumbranceOverdue;
}
