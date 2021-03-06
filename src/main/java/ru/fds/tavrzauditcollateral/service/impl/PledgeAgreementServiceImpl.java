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
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditPledgeAgreement;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditResult;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
@Qualifier("pledgeAgreementService")
public class PledgeAgreementServiceImpl implements ObjectAuditService {

    private final RepositoryAuditPledgeAgreement repositoryAuditPledgeAgreement;
    private final RepositoryAuditResult repositoryAuditResult;
    private final DescriptionResult descriptionResult;
    private final AuditAdvice auditAdvice;

    public PledgeAgreementServiceImpl(RepositoryAuditPledgeAgreement repositoryAuditPledgeAgreement,
                                      RepositoryAuditResult repositoryAuditResult,
                                      DescriptionResult descriptionResult,
                                      AuditAdvice auditAdvice) {
        this.repositoryAuditPledgeAgreement = repositoryAuditPledgeAgreement;
        this.repositoryAuditResult = repositoryAuditResult;
        this.descriptionResult = descriptionResult;
        this.auditAdvice = auditAdvice;
    }

    @Override
    @Transactional
    public void executeAuditAboutNewObject(Long id) {
        log.info("start executeAuditAboutNewObject. pledgeAgreementId: {}", id);

        Collection<AuditResult> auditResults = new ArrayList<>();

        repositoryAuditPledgeAgreement.isDateClosedOverDue(id).ifPresent(objectAudit -> {
            AuditResult auditResult = createAuditResultDateClosed(objectAudit);
            auditResults.add(auditResult);
        });

        repositoryAuditPledgeAgreement.isHaveNotLoanAgreements(id).ifPresent(objectAudit -> {
            AuditResult auditResult = createAuditResultNotExistLA(objectAudit);
            auditResults.add(auditResult);
        });

        repositoryAuditPledgeAgreement.isZeroZsDz(id).ifPresent(objectAudit -> {
            AuditResult auditResult = createAuditResultZeroZsDZ(objectAudit);
            auditResults.add(auditResult);
        });

        repositoryAuditPledgeAgreement.isZeroZsZZ(id).ifPresent(objectAudit -> {
            AuditResult auditResult = createAuditResultZeroZsZz(objectAudit);
            auditResults.add(auditResult);
        });

        repositoryAuditPledgeAgreement.isHaveNotPledgeSubjects(id).ifPresent(objectAudit -> {
            AuditResult auditResult = createAuditResultNotExistPS(objectAudit);

            auditResults.add(auditResult);
        });

        repositoryAuditResult.saveAll(auditResults);
    }

    @Override
    @Transactional
    public void executeAuditAboutExistObject(Long id) {
        log.info("start executeAuditAboutExistObject. pledgeAgreementId: {}", id);

        Collection<AuditResult> auditResults = new ArrayList<>();

        repositoryAuditPledgeAgreement.isDateClosedOverDue(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultDateClosed(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_AGREEMENT, id, TypeOfAudit.PLEDGE_AGREEMENT_DATE_CLOSED)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeAgreement.isHaveNotLoanAgreements(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultNotExistLA(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_AGREEMENT, id, TypeOfAudit.PLEDGE_AGREEMENT_NOT_EXIST_LA)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeAgreement.isZeroZsDz(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultZeroZsDZ(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_AGREEMENT, id, TypeOfAudit.PLEDGE_AGREEMENT_ZERO_ZS_DZ)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeAgreement.isZeroZsZZ(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultZeroZsZz(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_AGREEMENT, id, TypeOfAudit.PLEDGE_AGREEMENT_ZERO_ZS_ZZ)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeAgreement.isHaveNotPledgeSubjects(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultNotExistPS(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_AGREEMENT, id, TypeOfAudit.PLEDGE_AGREEMENT_NOT_EXIST_PS)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditResult.saveAll(auditResults);
    }

    @Override
    @Transactional
    public void executeAuditAboutAllObjects() {
        log.info("start executeAuditAboutAllObjects (Pledge Agreements)");

        Collection<AuditResult> auditResults = new ArrayList<>();

        repositoryAuditPledgeAgreement.getPledgeAgreementWithDateClosedOverdue().forEach(objectAudit ->
                auditResults.add(createAuditResultDateClosed(objectAudit)));

        repositoryAuditPledgeAgreement.getPledgeAgreementsWithoutLoanAgreement().forEach(objectAudit ->
                auditResults.add(createAuditResultNotExistLA(objectAudit)));

        repositoryAuditPledgeAgreement.getPledgeAgreementsWithZeroZsDZ().forEach(objectAudit ->
                auditResults.add(createAuditResultZeroZsDZ(objectAudit)));

        repositoryAuditPledgeAgreement.getPledgeAgreementsWithZeroZsZZ().forEach(objectAudit ->
                auditResults.add(createAuditResultZeroZsZz(objectAudit)));

        repositoryAuditPledgeAgreement.getPledgeAgreementsWithoutPledgeSubjects().forEach(objectAudit ->
                auditResults.add(createAuditResultNotExistPS(objectAudit)));

        repositoryAuditResult.saveAll(auditResults);
        log.info("end executeAuditAboutAllObjects (Pledge Agreements)");
    }

    @Override
    public Collection<AuditResult> getAuditResultsAboutObject(Long id) {
        return repositoryAuditResult.getExistAuditResults(TypeOfObject.PLEDGE_AGREEMENT, id);
    }

    @Override
    public Collection<AuditResult> getAuditResultsAboutObject(Long id, AuditStatus auditStatus) {
        return repositoryAuditResult.getExistAuditResults(TypeOfObject.PLEDGE_AGREEMENT, id, auditStatus);
    }

    private AuditResult createAuditResultDateClosed(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_AGREEMENT, objectAudit.getId(), TypeOfAudit.PLEDGE_AGREEMENT_DATE_CLOSED)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_AGREEMENT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_AGREEMENT_DATE_CLOSED)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.LOW)
                        .descriptionResult(descriptionResult.getPledgeAgreementDateClosed())
                        .advice(auditAdvice.getPledgeAgreementDateClosed())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultNotExistLA(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_AGREEMENT, objectAudit.getId(), TypeOfAudit.PLEDGE_AGREEMENT_NOT_EXIST_LA)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_AGREEMENT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_AGREEMENT_NOT_EXIST_LA)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.MEDIUM)
                        .descriptionResult(descriptionResult.getPledgeAgreementNotExistLA())
                        .advice(auditAdvice.getPledgeAgreementNotExistLA())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultZeroZsDZ(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_AGREEMENT, objectAudit.getId(), TypeOfAudit.PLEDGE_AGREEMENT_ZERO_ZS_DZ)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_AGREEMENT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_AGREEMENT_ZERO_ZS_DZ)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeAgreementZeroZzDZ())
                        .advice(auditAdvice.getPledgeAgreementZeroZzDZ())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultZeroZsZz(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_AGREEMENT, objectAudit.getId(), TypeOfAudit.PLEDGE_AGREEMENT_ZERO_ZS_ZZ)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_AGREEMENT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_AGREEMENT_ZERO_ZS_ZZ)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeAgreementZeroZsZz())
                        .advice(auditAdvice.getPledgeAgreementZeroZsZz())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultNotExistPS(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_AGREEMENT, objectAudit.getId(), TypeOfAudit.PLEDGE_AGREEMENT_NOT_EXIST_PS)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_AGREEMENT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_AGREEMENT_NOT_EXIST_PS)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.MEDIUM)
                        .descriptionResult(descriptionResult.getPledgeAgreementNotExistPS())
                        .advice(auditAdvice.getPledgeAgreementNotExistPS())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }
}
