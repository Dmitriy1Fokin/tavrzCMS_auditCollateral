package ru.fds.tavrzauditcollateral.dictionary;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class AuditAdvice {
    @Value("#{'${audit_advice.loan_agreement.date_closed}'.split(';')}")
    private List<String> loanAgreementDateClosed;
    @Value("#{'${audit_advice.loan_agreement.amount}'.split(';')}")
    private List<String> loanAgreementAmount;
    @Value("#{'${audit_advice.loan_agreement.not_exist_pa}'.split(';')}")
    private List<String> loanAgreementNotExistPA;
}
