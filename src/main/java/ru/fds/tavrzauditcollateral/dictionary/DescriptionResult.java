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


}
