package ru.fds.tavrzauditcollateral.service;

import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.dictionary.AuditLevel;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditLoanAgreement;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class LoanAgreementService {

    private final RepositoryAuditLoanAgreement repositoryAuditLoanAgreement;
    private final RepositoryAuditResult repositoryAuditResult;

    public LoanAgreementService(RepositoryAuditLoanAgreement repositoryAuditLoanAgreement,
                                RepositoryAuditResult repositoryAuditResult) {
        this.repositoryAuditLoanAgreement = repositoryAuditLoanAgreement;
        this.repositoryAuditResult = repositoryAuditResult;
    }

    public void doAuditResultAboutNewLoanAgreement(Long loanAgreementId){
        Collection<AuditResult> auditResults = new ArrayList<>();
        repositoryAuditLoanAgreement.isDateClosedOverDue(loanAgreementId).ifPresent(loanAgreementAuditDateClosed -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(loanAgreementAuditDateClosed.getTypeOfObject())
                    .objectId(loanAgreementAuditDateClosed.getId())
                    .nameOfObject(loanAgreementAuditDateClosed.getNameObject())
                    .fieldNameWithError(loanAgreementAuditDateClosed.fieldNameWithError())
                    .valueInField(loanAgreementAuditDateClosed.getValueInField())
                    .auditLevel(AuditLevel.LOW)
                    .descriptionResult("Кредитный договор не закрыт")
                    .advice(List.of("Закрыть договор", "Изменить дату окончания КД", "Присвоить статус - Проблемный"))
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditLoanAgreement.isLowCollateralSum(loanAgreementId).ifPresent(loanAgreementAuditLowCollateralValue -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(loanAgreementAuditLowCollateralValue.getTypeOfObject())
                    .objectId(loanAgreementAuditLowCollateralValue.getId())
                    .nameOfObject(loanAgreementAuditLowCollateralValue.getNameObject())
                    .fieldNameWithError(loanAgreementAuditLowCollateralValue.fieldNameWithError())
                    .valueInField(loanAgreementAuditLowCollateralValue.getValueInField())
                    .auditLevel(AuditLevel.LOW)
                    .descriptionResult("Обеспеченность договора ниже кредитной задолженности")
                    .advice(List.of("Снизить кредитную задолженность", "Предоставить дополнитнльгый залог"))
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditLoanAgreement.isNotHavePledgeAgreements(loanAgreementId).ifPresent(loanAgreementWithoutPA -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(loanAgreementWithoutPA.getTypeOfObject())
                    .objectId(loanAgreementWithoutPA.getId())
                    .nameOfObject(loanAgreementWithoutPA.getNameObject())
                    .fieldNameWithError(loanAgreementWithoutPA.fieldNameWithError())
                    .valueInField(loanAgreementWithoutPA.getValueInField())
                    .auditLevel(AuditLevel.MEDIUM)
                    .descriptionResult("Отсутствуют договоры залога")
                    .advice(List.of("Заключить договор залога", "Закрыть кредитный договор"))
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditResult.saveAll(auditResults);
    }
}
