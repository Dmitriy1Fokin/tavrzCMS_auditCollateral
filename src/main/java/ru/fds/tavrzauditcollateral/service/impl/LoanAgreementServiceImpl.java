package ru.fds.tavrzauditcollateral.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.tavrzauditcollateral.dictionary.AuditAdvice;
import ru.fds.tavrzauditcollateral.dictionary.AuditLevel;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.dictionary.DescriptionResult;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfAudit;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;
import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditLoanAgreement;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditResult;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
@Qualifier("loanAgreementService")
public class LoanAgreementServiceImpl implements ObjectAuditService {

    private final RepositoryAuditLoanAgreement repositoryAuditLoanAgreement;
    private final RepositoryAuditResult repositoryAuditResult;
    private final DescriptionResult descriptionResult;
    private final AuditAdvice auditAdvice;

    public LoanAgreementServiceImpl(RepositoryAuditLoanAgreement repositoryAuditLoanAgreement,
                                    RepositoryAuditResult repositoryAuditResult,
                                    DescriptionResult descriptionResult,
                                    AuditAdvice auditAdvice) {
        this.repositoryAuditLoanAgreement = repositoryAuditLoanAgreement;
        this.repositoryAuditResult = repositoryAuditResult;
        this.descriptionResult = descriptionResult;
        this.auditAdvice = auditAdvice;
    }

    @Override
    @Transactional
    public void executeAuditAboutNewObject(Long id){
        Collection<AuditResult> auditResults = new ArrayList<>();
        repositoryAuditLoanAgreement.isDateClosedOverDue(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.LOAN_AGREEMENT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.LOAN_AGREEMENT_DATE_CLOSED)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.LOW)
                    .descriptionResult(descriptionResult.getLoanAgreementDateClosed())
                    .advice(auditAdvice.getLoanAgreementDateClosed())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditLoanAgreement.isLowCollateralSum(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.LOAN_AGREEMENT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.LOAN_AGREEMENT_AMOUNT_LA)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.LOW)
                    .descriptionResult(descriptionResult.getLoanAgreementAmount())
                    .advice(auditAdvice.getLoanAgreementAmount())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditLoanAgreement.isHaveNotPledgeAgreements(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.LOAN_AGREEMENT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.LOAN_AGREEMENT_NOT_EXIST_PA)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.MEDIUM)
                    .descriptionResult(descriptionResult.getLoanAgreementNotExistPA())
                    .advice(auditAdvice.getLoanAgreementNotExistPA())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        log.debug("executeAuditAboutNewObject. Collection<AuditResult>: {}", auditResults.toString());
        repositoryAuditResult.saveAll(auditResults);
    }

    @Override
    @Transactional
    public void executeAuditAboutExistObject(Long id){
        Collection<AuditResult> auditResults = new ArrayList<>();

        repositoryAuditLoanAgreement.isDateClosedOverDue(id)
                .ifPresentOrElse(objectAudit ->
                        auditResults.add(createAuditResultDateClosed(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.LOAN_AGREEMENT, id, TypeOfAudit.LOAN_AGREEMENT_DATE_CLOSED)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                            })
                );

        repositoryAuditLoanAgreement.isLowCollateralSum(id)
                .ifPresentOrElse(objectAudit ->
                        auditResults.add(createAuditResultLowCollateralSum(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.LOAN_AGREEMENT, id, TypeOfAudit.LOAN_AGREEMENT_AMOUNT_LA)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                            })
                );

        repositoryAuditLoanAgreement.isHaveNotPledgeAgreements(id)
                .ifPresentOrElse(objectAudit ->
                        auditResults.add(createAuditResultHaveNotPA(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.LOAN_AGREEMENT, id, TypeOfAudit.LOAN_AGREEMENT_NOT_EXIST_PA)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        log.debug("executeAuditAboutExistObject. Collection<AuditResult>: {}", auditResults.toString());
        repositoryAuditResult.saveAll(auditResults);
    }

    @Override
    @Transactional
    public void executeAuditAboutAllObjects(){
        Collection<AuditResult> auditResults = new ArrayList<>();

        repositoryAuditLoanAgreement.getLoanAgreementWithDateClosedOverdue().forEach(objectAudit ->
            auditResults.add(createAuditResultDateClosed(objectAudit)));

        repositoryAuditLoanAgreement.getLoanAgreementWithLowCollateralValue().forEach(objectAudit ->
            auditResults.add(createAuditResultLowCollateralSum(objectAudit)));

        repositoryAuditLoanAgreement.getLoanAgreementWithoutPledge().forEach(objectAudit ->
            auditResults.add(createAuditResultHaveNotPA(objectAudit)));

        log.debug("executeAuditAboutAllObjects. Collection<AuditResult>: {}", auditResults.toString());
        repositoryAuditResult.saveAll(auditResults);
    }

    @Override
    public Collection<AuditResult> getAuditResultsAboutObject(Long id){
        return repositoryAuditResult.getExistAuditResults(TypeOfObject.LOAN_AGREEMENT, id);
    }

    @Override
    public Collection<AuditResult> getAuditResultsAboutObject(Long id, AuditStatus auditStatus){
        return repositoryAuditResult.getExistAuditResults(TypeOfObject.LOAN_AGREEMENT, id, auditStatus);
    }

    private AuditResult createAuditResultDateClosed(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.LOAN_AGREEMENT, objectAudit.getId(), TypeOfAudit.LOAN_AGREEMENT_DATE_CLOSED)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())){
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                            .date(LocalDate.now())
                            .typeOfObject(TypeOfObject.LOAN_AGREEMENT)
                            .objectId(objectAudit.getId())
                            .nameOfObject(objectAudit.getNameObject())
                            .typeOfAudit(TypeOfAudit.LOAN_AGREEMENT_DATE_CLOSED)
                            .valueInField(objectAudit.getWrongValueInField())
                            .auditLevel(AuditLevel.LOW)
                            .descriptionResult(descriptionResult.getLoanAgreementDateClosed())
                            .advice(auditAdvice.getLoanAgreementDateClosed())
                            .auditStatus(AuditStatus.ACTUAL)
                            .build());
    }

    private AuditResult createAuditResultLowCollateralSum(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.LOAN_AGREEMENT, objectAudit.getId(), TypeOfAudit.LOAN_AGREEMENT_AMOUNT_LA)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())){
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                            .date(LocalDate.now())
                            .typeOfObject(TypeOfObject.LOAN_AGREEMENT)
                            .objectId(objectAudit.getId())
                            .nameOfObject(objectAudit.getNameObject())
                            .typeOfAudit(TypeOfAudit.LOAN_AGREEMENT_AMOUNT_LA)
                            .valueInField(objectAudit.getWrongValueInField())
                            .auditLevel(AuditLevel.LOW)
                            .descriptionResult(descriptionResult.getLoanAgreementAmount())
                            .advice(auditAdvice.getLoanAgreementAmount())
                            .auditStatus(AuditStatus.ACTUAL)
                            .build());
    }

    private AuditResult createAuditResultHaveNotPA(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.LOAN_AGREEMENT, objectAudit.getId(), TypeOfAudit.LOAN_AGREEMENT_NOT_EXIST_PA)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.LOAN_AGREEMENT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.LOAN_AGREEMENT_NOT_EXIST_PA)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.MEDIUM)
                        .descriptionResult(descriptionResult.getLoanAgreementNotExistPA())
                        .advice(auditAdvice.getLoanAgreementNotExistPA())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }
}
