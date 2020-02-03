package ru.fds.tavrzauditcollateral.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.fds.tavrzauditcollateral.dictionary.AuditLevel;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditLoanAgreement;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditResult;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Qualifier("loanAgreementService")
public class LoanAgreementServiceImpl implements ObjectAuditService {

    private final RepositoryAuditLoanAgreement repositoryAuditLoanAgreement;
    private final RepositoryAuditResult repositoryAuditResult;

    public LoanAgreementServiceImpl(RepositoryAuditLoanAgreement repositoryAuditLoanAgreement,
                                    RepositoryAuditResult repositoryAuditResult) {
        this.repositoryAuditLoanAgreement = repositoryAuditLoanAgreement;
        this.repositoryAuditResult = repositoryAuditResult;
    }

    @Override
    public void doAuditAboutNewObject(Long id){
        Collection<AuditResult> auditResults = new ArrayList<>();
        repositoryAuditLoanAgreement.isDateClosedOverDue(id).ifPresent(loanAgreementAuditDateClosed -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.LOAN_AGREEMENT)
                    .objectId(loanAgreementAuditDateClosed.getId())
                    .nameOfObject(loanAgreementAuditDateClosed.getNameObject())
                    .fieldNameWithError("dateBeginLA")
                    .valueInField(loanAgreementAuditDateClosed.getWrongValueInField())
                    .auditLevel(AuditLevel.LOW)
                    .descriptionResult("Кредитный договор не закрыт")
                    .advice(List.of("Закрыть договор", "Изменить дату окончания КД", "Присвоить статус - Проблемный"))
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditLoanAgreement.isLowCollateralSum(id).ifPresent(loanAgreementAuditLowCollateralValue -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.LOAN_AGREEMENT)
                    .objectId(loanAgreementAuditLowCollateralValue.getId())
                    .nameOfObject(loanAgreementAuditLowCollateralValue.getNameObject())
                    .fieldNameWithError("amountLA")
                    .valueInField(loanAgreementAuditLowCollateralValue.getWrongValueInField())
                    .auditLevel(AuditLevel.LOW)
                    .descriptionResult("Обеспеченность договора ниже кредитной задолженности")
                    .advice(List.of("Снизить кредитную задолженность", "Предоставить дополнитнльгый залог"))
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditLoanAgreement.isHaveNotPledgeAgreements(id).ifPresent(loanAgreementWithoutPA -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.LOAN_AGREEMENT)
                    .objectId(loanAgreementWithoutPA.getId())
                    .nameOfObject(loanAgreementWithoutPA.getNameObject())
                    .fieldNameWithError("")
                    .valueInField(loanAgreementWithoutPA.getWrongValueInField())
                    .auditLevel(AuditLevel.MEDIUM)
                    .descriptionResult("Отсутствуют договоры залога")
                    .advice(List.of("Заключить договор залога", "Закрыть кредитный договор"))
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        log.debug("doAuditResultAboutNewLoanAgreement. Collection<AuditResult>: " + auditResults.toString());
        repositoryAuditResult.saveAll(auditResults);
    }

    @Override
    public void doAuditAboutExitObject(Long id){

    }

    @Override
    public void doAuditAboutAllObjects(){

    }

    @Override
    public Collection<AuditResult> getAuditResultsAboutObject(Long id){
        return null;
    }

    @Override
    public Collection<AuditResult> getAuditResultsAboutObject(Long id, AuditStatus auditStatus){
        return null;
    }
}
