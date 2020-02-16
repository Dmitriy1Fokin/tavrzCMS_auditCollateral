package ru.fds.tavrzauditcollateral.dictionary;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public final class DescriptionResult {

    @Value("${description_audit_result.loan_agreement.date_closed}")
    private String loanAgreementDateClosed;
    @Value("${description_audit_result.loan_agreement.amount}")
    private String loanAgreementAmount;
    @Value("${description_audit_result.loan_agreement.not_exist_pa}")
    private String loanAgreementNotExistPA;

    @Value("${description_audit_result.pledge_agreement.date_closed}")
    private String pledgeAgreementDateClosed;
    @Value("${description_audit_result.pledge_agreement.not_exist_la}")
    private String pledgeAgreementNotExistLA;
    @Value("${description_audit_result.pledge_agreement.zero_zs_dz}")
    private String pledgeAgreementZeroZzDZ;
    @Value("${description_audit_result.pledge_agreement.zero_zs_zz}")
    private String pledgeAgreementZeroZsZz;
    @Value("${description_audit_result.pledge_agreement.not_exist_ps}")
    private String pledgeAgreementNotExistPS;

    @Value("${description_audit_result.pledge_subject.low_liquidity_not_zero_ss}")
    private String pledgeSubjectLowLiquidityNotZeroSS;
    @Value("${description_audit_result.pledge_subject.zero_zs_dz}")
    private String pledgeSubjectZeroZsDz;
    @Value("${description_audit_result.pledge_subject.zero_zs_zz}")
    private String pledgeSubjectZeroZsZz;
    @Value("${description_audit_result.pledge_subject.zero_rs_dz}")
    private String pledgeSubjectZeroRsDz;
    @Value("${description_audit_result.pledge_subject.zero_rs_zz}")
    private String pledgeSubjectZeroRsZz;
    @Value("${description_audit_result.pledge_subject.zero_ss}")
    private String pledgeSubjectZeroSs;
    @Value("${description_audit_result.pledge_subject.monitoring_overdue}")
    private String pledgeSubjectMonitoringOverdue;
    @Value("${description_audit_result.pledge_subject.conclusion_overdue}")
    private String pledgeSubjectConclusionOverdue;
    @Value("${description_audit_result.pledge_subject.losing}")
    private String pledgeSubjectLosing;
    @Value("${description_audit_result.pledge_subject.not_exist_insurance}")
    private String pledgeSubjectNotExistInsurance;
    @Value("${description_audit_result.pledge_subject.not_exist_encumbrance}")
    private String pledgeSubjectNotExistEncumbrance;
    @Value("${description_audit_result.pledge_subject.exist_other_encumbrance}")
    private String pledgeSubjectExistOtherEncumbrance;
    @Value("${description_audit_result.pledge_subject.insurance_overdue}")
    private String pledgeSubjectInsuranceOverdue;
    @Value("${description_audit_result.pledge_subject.encumbrance_overdue}")
    private String pledgeSubjectEncumbranceOverdue;

}
