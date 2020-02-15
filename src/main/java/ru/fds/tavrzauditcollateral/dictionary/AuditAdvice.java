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
}
