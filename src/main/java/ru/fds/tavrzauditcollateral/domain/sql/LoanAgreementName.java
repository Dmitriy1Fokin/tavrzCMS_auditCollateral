package ru.fds.tavrzauditcollateral.domain.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LoanAgreementName {

    private String numLA;
    private LocalDate dateBegin;

    @Override
    public String toString() {
        return numLA + " от " + dateBegin.toString();
    }
}
