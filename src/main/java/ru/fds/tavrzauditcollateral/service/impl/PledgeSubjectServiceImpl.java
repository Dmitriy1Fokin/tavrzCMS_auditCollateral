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
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditPledgeSubject;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditResult;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
@Qualifier("pledgeSubjectService")
public class PledgeSubjectServiceImpl implements ObjectAuditService {

    private final RepositoryAuditPledgeSubject repositoryAuditPledgeSubject;
    private final RepositoryAuditResult repositoryAuditResult;
    private final DescriptionResult descriptionResult;
    private final AuditAdvice auditAdvice;

    public PledgeSubjectServiceImpl(RepositoryAuditPledgeSubject repositoryAuditPledgeSubject,
                                    RepositoryAuditResult repositoryAuditResult,
                                    DescriptionResult descriptionResult,
                                    AuditAdvice auditAdvice) {
        this.repositoryAuditPledgeSubject = repositoryAuditPledgeSubject;
        this.repositoryAuditResult = repositoryAuditResult;
        this.descriptionResult = descriptionResult;
        this.auditAdvice = auditAdvice;
    }

    @Override
    @Transactional
    public void executeAuditAboutNewObject(Long id) {
        Collection<AuditResult> auditResults = new ArrayList<>();

        repositoryAuditPledgeSubject.isHaveLowLiquidityAndNotZeroSS(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_LOW_LIQUIDITY_NOT_ZERO_SS)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.HIGH)
                    .descriptionResult(descriptionResult.getPledgeSubjectLowLiquidityNotZeroSS())
                    .advice(auditAdvice.getPledgeSubjectLowLiquidityNotZeroSS())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isHaveZeroZsDz(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ZERO_ZS_DZ)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.HIGH)
                    .descriptionResult(descriptionResult.getPledgeSubjectZeroZsDz())
                    .advice(auditAdvice.getPledgeSubjectZeroZsDz())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isHaveZeroZsZz(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ZERO_ZS_ZZ)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.HIGH)
                    .descriptionResult(descriptionResult.getPledgeSubjectZeroZsZz())
                    .advice(auditAdvice.getPledgeSubjectZeroZsZz())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isHaveZeroRsDz(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ZERO_RS_DZ)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.MEDIUM)
                    .descriptionResult(descriptionResult.getPledgeSubjectZeroRsDz())
                    .advice(auditAdvice.getPledgeSubjectZeroRsDz())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isHaveZeroRsZz(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ZERO_RS_ZZ)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.MEDIUM)
                    .descriptionResult(descriptionResult.getPledgeSubjectZeroRsZz())
                    .advice(auditAdvice.getPledgeSubjectZeroRsZz())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isHaveZeroSs(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ZERO_SS)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.MEDIUM)
                    .descriptionResult(descriptionResult.getPledgeSubjectZeroSs())
                    .advice(auditAdvice.getPledgeSubjectZeroSs())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isMonitoringOverdue(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_MONITORING_OVERDUE)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.HIGH)
                    .descriptionResult(descriptionResult.getPledgeSubjectMonitoringOverdue())
                    .advice(auditAdvice.getPledgeSubjectMonitoringOverdue())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isConclusionOverdue(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_CONCLUSION_OVERDUE)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.HIGH)
                    .descriptionResult(descriptionResult.getPledgeSubjectConclusionOverdue())
                    .advice(auditAdvice.getPledgeSubjectConclusionOverdue())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isLosing(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_LOSING)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.MEDIUM)
                    .descriptionResult(descriptionResult.getPledgeSubjectLosing())
                    .advice(auditAdvice.getPledgeSubjectLosing())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isHaveNotInsurance(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_NOT_EXIST_INSURANCE)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.HIGH)
                    .descriptionResult(descriptionResult.getPledgeSubjectNotExistInsurance())
                    .advice(auditAdvice.getPledgeSubjectNotExistInsurance())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isHaveNotOurEncumbrance(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_NOT_EXIST_ENCUMBRANCE)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.HIGH)
                    .descriptionResult(descriptionResult.getPledgeSubjectNotExistEncumbrance())
                    .advice(auditAdvice.getPledgeSubjectNotExistEncumbrance())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isHaveOtherEncumbrance(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_EXIST_OTHER_ENCUMBRANCE)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.HIGH)
                    .descriptionResult(descriptionResult.getPledgeSubjectExistOtherEncumbrance())
                    .advice(auditAdvice.getPledgeSubjectExistOtherEncumbrance())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isEncumbranceOverdue(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ENCUMBRANCE_OVERDUE)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.HIGH)
                    .descriptionResult(descriptionResult.getPledgeSubjectEncumbranceOverdue())
                    .advice(auditAdvice.getPledgeSubjectEncumbranceOverdue())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        repositoryAuditPledgeSubject.isInsuranceOverdue(id).ifPresent(objectAudit -> {
            AuditResult auditResult = AuditResult.builder()
                    .date(LocalDate.now())
                    .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                    .objectId(objectAudit.getId())
                    .nameOfObject(objectAudit.getNameObject())
                    .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_INSURANCE_OVERDUE)
                    .valueInField(objectAudit.getWrongValueInField())
                    .auditLevel(AuditLevel.HIGH)
                    .descriptionResult(descriptionResult.getPledgeSubjectInsuranceOverdue())
                    .advice(auditAdvice.getPledgeSubjectInsuranceOverdue())
                    .auditStatus(AuditStatus.ACTUAL)
                    .build();

            auditResults.add(auditResult);
        });

        log.debug("executeAuditAboutNewObject. Collection<AuditResult>: {}", auditResults.toString());
        repositoryAuditResult.saveAll(auditResults);
    }

    @Override
    @Transactional
    public void executeAuditAboutExistObject(Long id) {
        Collection<AuditResult> auditResults = new ArrayList<>();

        repositoryAuditPledgeSubject.isHaveLowLiquidityAndNotZeroSS(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultLowLiquidityAndNotZeroSS(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_LOW_LIQUIDITY_NOT_ZERO_SS)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isHaveZeroZsDz(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultHaveZeroZsDz(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_ZERO_ZS_DZ)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isHaveZeroZsZz(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultHaveZeroZsZz(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_ZERO_ZS_ZZ)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isHaveZeroRsDz(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultHaveZeroRsDz(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_ZERO_RS_DZ)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isHaveZeroRsZz(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultHaveZeroRsZz(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_ZERO_RS_ZZ)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isHaveZeroSs(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultHaveZeroSs(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_ZERO_SS)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isMonitoringOverdue(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultMonitoringOverdue(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_MONITORING_OVERDUE)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isConclusionOverdue(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultConclusionOverdue(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_CONCLUSION_OVERDUE)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isLosing(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultLosing(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_LOSING)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isHaveNotInsurance(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultHaveNotInsurance(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_NOT_EXIST_INSURANCE)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isHaveNotOurEncumbrance(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultHaveNotOurEncumbrance(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_NOT_EXIST_ENCUMBRANCE)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isHaveOtherEncumbrance(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultHaveOtherEncumbrance(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_EXIST_OTHER_ENCUMBRANCE)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isEncumbranceOverdue(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultEncumbranceOverdue(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_ENCUMBRANCE_OVERDUE)
                                        .ifPresent(auditResult -> {
                                            auditResult.setAuditStatus(AuditStatus.CLOSED);
                                            auditResults.add(auditResult);
                                        })
                );

        repositoryAuditPledgeSubject.isInsuranceOverdue(id)
                .ifPresentOrElse(objectAudit ->
                                auditResults.add(createAuditResultInsuranceOverdue(objectAudit)),
                        () ->
                                repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, id, TypeOfAudit.PLEDGE_SUBJECT_INSURANCE_OVERDUE)
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
    public void executeAuditAboutAllObjects() {
        Collection<AuditResult> auditResults = new ArrayList<>();

        repositoryAuditPledgeSubject.getPledgeSubjectWithLowLiquidityAndNotZeroSs().forEach(objectAudit ->
                auditResults.add(createAuditResultLowLiquidityAndNotZeroSS(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsWithZeroZsDz().forEach(objectAudit ->
                auditResults.add(createAuditResultHaveZeroZsDz(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsWithZeroZsZz().forEach(objectAudit ->
                auditResults.add(createAuditResultHaveZeroZsZz(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsWithZeroRsDz().forEach(objectAudit ->
                auditResults.add(createAuditResultHaveZeroRsDz(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsWithZeroRsZz().forEach(objectAudit ->
                auditResults.add(createAuditResultHaveZeroRsZz(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsWithZeroSs().forEach(objectAudit ->
                auditResults.add(createAuditResultHaveZeroSs(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsWithMonitoringOverdue().forEach(objectAudit ->
                auditResults.add(createAuditResultMonitoringOverdue(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsWithConclusionOverdue().forEach(objectAudit ->
                auditResults.add(createAuditResultConclusionOverdue(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsLosing().forEach(objectAudit ->
                auditResults.add(createAuditResultLosing(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectWithoutInsurance().forEach(objectAudit ->
                auditResults.add(createAuditResultHaveNotInsurance(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectWithoutOurEncumbrance().forEach(objectAudit ->
                auditResults.add(createAuditResultHaveNotOurEncumbrance(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsWithOtherEncumbrance().forEach(objectAudit ->
                auditResults.add(createAuditResultHaveOtherEncumbrance(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsWithEncumbranceOverdue().forEach(objectAudit ->
                auditResults.add(createAuditResultEncumbranceOverdue(objectAudit)));

        repositoryAuditPledgeSubject.getPledgeSubjectsWithInsuranceOverdue().forEach(objectAudit ->
                auditResults.add(createAuditResultInsuranceOverdue(objectAudit)));

        log.debug("executeAuditAboutAllObjects. Collection<AuditResult>: {}", auditResults.toString());
        repositoryAuditResult.saveAll(auditResults);
    }

    @Override
    public Collection<AuditResult> getAuditResultsAboutObject(Long id) {
        return repositoryAuditResult.getExistAuditResults(TypeOfObject.PLEDGE_SUBJECT, id);
    }

    @Override
    public Collection<AuditResult> getAuditResultsAboutObject(Long id, AuditStatus auditStatus) {
        return repositoryAuditResult.getExistAuditResults(TypeOfObject.PLEDGE_SUBJECT, id, auditStatus);
    }

    private AuditResult createAuditResultLowLiquidityAndNotZeroSS(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_LOW_LIQUIDITY_NOT_ZERO_SS)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_LOW_LIQUIDITY_NOT_ZERO_SS)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeSubjectLowLiquidityNotZeroSS())
                        .advice(auditAdvice.getPledgeSubjectLowLiquidityNotZeroSS())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultHaveZeroZsDz(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_ZERO_ZS_DZ)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ZERO_ZS_DZ)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeSubjectZeroZsDz())
                        .advice(auditAdvice.getPledgeSubjectZeroZsDz())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultHaveZeroZsZz(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_ZERO_ZS_ZZ)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ZERO_ZS_ZZ)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeSubjectZeroZsZz())
                        .advice(auditAdvice.getPledgeSubjectZeroZsZz())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultHaveZeroRsDz(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_ZERO_RS_DZ)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ZERO_RS_DZ)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.MEDIUM)
                        .descriptionResult(descriptionResult.getPledgeSubjectZeroRsDz())
                        .advice(auditAdvice.getPledgeSubjectZeroRsDz())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultHaveZeroRsZz(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_ZERO_RS_ZZ)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ZERO_RS_ZZ)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.MEDIUM)
                        .descriptionResult(descriptionResult.getPledgeSubjectZeroRsZz())
                        .advice(auditAdvice.getPledgeSubjectZeroRsZz())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultHaveZeroSs(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_ZERO_SS)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ZERO_SS)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.MEDIUM)
                        .descriptionResult(descriptionResult.getPledgeSubjectZeroSs())
                        .advice(auditAdvice.getPledgeSubjectZeroSs())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultMonitoringOverdue(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_MONITORING_OVERDUE)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_MONITORING_OVERDUE)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeSubjectMonitoringOverdue())
                        .advice(auditAdvice.getPledgeSubjectMonitoringOverdue())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultConclusionOverdue(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_CONCLUSION_OVERDUE)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_CONCLUSION_OVERDUE)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeSubjectConclusionOverdue())
                        .advice(auditAdvice.getPledgeSubjectConclusionOverdue())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultLosing(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_LOSING)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_LOSING)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.MEDIUM)
                        .descriptionResult(descriptionResult.getPledgeSubjectLosing())
                        .advice(auditAdvice.getPledgeSubjectLosing())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultHaveNotInsurance(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_NOT_EXIST_INSURANCE)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_NOT_EXIST_INSURANCE)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeSubjectNotExistInsurance())
                        .advice(auditAdvice.getPledgeSubjectNotExistInsurance())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultHaveNotOurEncumbrance(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_NOT_EXIST_ENCUMBRANCE)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_NOT_EXIST_ENCUMBRANCE)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeSubjectNotExistEncumbrance())
                        .advice(auditAdvice.getPledgeSubjectNotExistEncumbrance())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultHaveOtherEncumbrance(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_EXIST_OTHER_ENCUMBRANCE)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_EXIST_OTHER_ENCUMBRANCE)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeSubjectExistOtherEncumbrance())
                        .advice(auditAdvice.getPledgeSubjectExistOtherEncumbrance())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultEncumbranceOverdue(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_ENCUMBRANCE_OVERDUE)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_ENCUMBRANCE_OVERDUE)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeSubjectEncumbranceOverdue())
                        .advice(auditAdvice.getPledgeSubjectEncumbranceOverdue())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }

    private AuditResult createAuditResultInsuranceOverdue(ObjectAudit objectAudit){
        return repositoryAuditResult.getExistAuditResult(TypeOfObject.PLEDGE_SUBJECT, objectAudit.getId(), TypeOfAudit.PLEDGE_SUBJECT_INSURANCE_OVERDUE)
                .map(auditResult -> {
                    if(!objectAudit.getWrongValueInField().equals(auditResult.getValueInField())) {
                        auditResult.setValueInField(objectAudit.getWrongValueInField());
                    }
                    return auditResult;
                }).orElse(AuditResult.builder()
                        .date(LocalDate.now())
                        .typeOfObject(TypeOfObject.PLEDGE_SUBJECT)
                        .objectId(objectAudit.getId())
                        .nameOfObject(objectAudit.getNameObject())
                        .typeOfAudit(TypeOfAudit.PLEDGE_SUBJECT_INSURANCE_OVERDUE)
                        .valueInField(objectAudit.getWrongValueInField())
                        .auditLevel(AuditLevel.HIGH)
                        .descriptionResult(descriptionResult.getPledgeSubjectInsuranceOverdue())
                        .advice(auditAdvice.getPledgeSubjectInsuranceOverdue())
                        .auditStatus(AuditStatus.ACTUAL)
                        .build());
    }
}
