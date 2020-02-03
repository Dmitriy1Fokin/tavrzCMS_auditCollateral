package ru.fds.tavrzauditcollateral.domain.sql;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class LoanAgreementAuditDateClosed implements ObjectAudit {

    private Long loanAgreementId;
    private LoanAgreementName loanAgreementName;
    private LocalDate dateEndLA;

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
        return "dateEndLA";
    }

    @Override
    public String getValueInField() {
        return dateEndLA.toString();
    }
}
