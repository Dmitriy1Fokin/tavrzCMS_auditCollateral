package ru.fds.tavrzauditcollateral.domain.sql;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;

@Getter
@Builder
@ToString
public class LoanAgreementWithoutPA implements ObjectAudit {

    private Long loanAgreementId;
    private LoanAgreementName loanAgreementName;
    private Integer countOfPledgeAgreements;

    @Override
    public TypeOfObject getTypeOfObject() {
        return TypeOfObject.LOAN_AGREEMENT;
    }

    @Override
    public Long getId() {
        return loanAgreementId;
    }

    @Override
    public String getNameObject() {
        return loanAgreementName.toString();
    }

    @Override
    public String fieldNameWithError() {
        return "countOfPledgeAgreements";
    }

    @Override
    public String getValueInField() {
        return countOfPledgeAgreements.toString();
    }
}
