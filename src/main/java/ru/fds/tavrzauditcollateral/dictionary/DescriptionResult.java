package ru.fds.tavrzauditcollateral.dictionary;

import lombok.Getter;
import lombok.Setter;
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


}
