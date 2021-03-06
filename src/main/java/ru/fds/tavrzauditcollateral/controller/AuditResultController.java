package ru.fds.tavrzauditcollateral.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.dto.AuditResultConverter;
import ru.fds.tavrzauditcollateral.dto.AuditResultDto;
import ru.fds.tavrzauditcollateral.service.AuditResultService;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;

import java.util.Collection;

@RestController
@RequestMapping("/audit")
public class AuditResultController {

    private final ObjectAuditService loanAgreementService;
    private final ObjectAuditService pledgeAgreementService;
    private final ObjectAuditService pledgeSubjectService;
    private final AuditResultService auditResultService;
    private final AuditResultConverter auditResultConverter;

    public AuditResultController(@Qualifier("loanAgreementService") ObjectAuditService loanAgreementService,
                                 @Qualifier("pledgeAgreementService") ObjectAuditService pledgeAgreementService,
                                 @Qualifier("pledgeSubjectService") ObjectAuditService pledgeSubjectService,
                                 AuditResultService auditResultService,
                                 AuditResultConverter auditResultConverter) {
        this.loanAgreementService = loanAgreementService;
        this.pledgeAgreementService = pledgeAgreementService;
        this.pledgeSubjectService = pledgeSubjectService;
        this.auditResultService = auditResultService;
        this.auditResultConverter = auditResultConverter;
    }

    @GetMapping("/loan_agreement")
    public Collection<AuditResultDto> getAuditResultAboutLoanAgreement(@RequestParam("loanAgreementId") Long loanAgreementId){
        return auditResultConverter.toDto(loanAgreementService.getAuditResultsAboutObject(loanAgreementId));
    }

    @GetMapping("/loan_agreement/actual")
    public Collection<AuditResultDto> getActualAuditResultAboutLoanAgreement(@RequestParam("loanAgreementId") Long loanAgreementId){
        return auditResultConverter.toDto(loanAgreementService.getAuditResultsAboutObject(loanAgreementId, AuditStatus.ACTUAL));
    }

    @GetMapping("/loan_agreement/ignore")
    public Collection<AuditResultDto> getIgnoreAuditResultAboutLoanAgreement(@RequestParam("loanAgreementId") Long loanAgreementId){
        return auditResultConverter.toDto(loanAgreementService.getAuditResultsAboutObject(loanAgreementId, AuditStatus.IGNORE));
    }

    @GetMapping("/pledge_agreement")
    public Collection<AuditResultDto> getAuditResultAboutPledgeAgreement(@RequestParam("pledgeAgreementId") Long pledgeAgreementId){
        return auditResultConverter.toDto(pledgeAgreementService.getAuditResultsAboutObject(pledgeAgreementId));
    }

    @GetMapping("/pledge_agreement/actual")
    public Collection<AuditResultDto> getActualAuditResultAboutPledgeAgreement(@RequestParam("pledgeAgreementId") Long pledgeAgreementId){
        return auditResultConverter.toDto(pledgeAgreementService.getAuditResultsAboutObject(pledgeAgreementId, AuditStatus.ACTUAL));
    }

    @GetMapping("/pledge_agreement/ignore")
    public Collection<AuditResultDto> getIgnoreAuditResultAboutPledgeAgreement(@RequestParam("pledgeAgreementId") Long pledgeAgreementId){
        return auditResultConverter.toDto(pledgeAgreementService.getAuditResultsAboutObject(pledgeAgreementId, AuditStatus.IGNORE));
    }

    @GetMapping("/pledge_subject")
    public Collection<AuditResultDto> getAuditResultAboutPledgeSubject(@RequestParam("pledgeSubjectId") Long pledgeSubjectId){
        return auditResultConverter.toDto(pledgeSubjectService.getAuditResultsAboutObject(pledgeSubjectId));
    }

    @GetMapping("/pledge_subject/actual")
    public Collection<AuditResultDto> getActualAuditResultAboutPledgeSubject(@RequestParam("pledgeSubjectId") Long pledgeSubjectId){
        return auditResultConverter.toDto(pledgeSubjectService.getAuditResultsAboutObject(pledgeSubjectId, AuditStatus.ACTUAL));
    }

    @GetMapping("/pledge_subject/ignore")
    public Collection<AuditResultDto> getIgnoreAuditResultAboutPledgeSubject(@RequestParam("pledgeSubjectId") Long pledgeSubjectId){
        return auditResultConverter.toDto(pledgeSubjectService.getAuditResultsAboutObject(pledgeSubjectId, AuditStatus.IGNORE));
    }

    @GetMapping()
    public Collection<AuditResultDto> getAuditResult(Pageable pageable){
        return auditResultConverter.toDto(auditResultService.getAllAuditResults(pageable));
    }

    @PutMapping("/ignore")
    public AuditResultDto setIgnore(@RequestParam("auditResultId") String auditResultId){
        return auditResultConverter.toDto(auditResultService.setAuditStatus(auditResultId, AuditStatus.IGNORE));
    }

    @PutMapping("/actual")
    public AuditResultDto setActual(@RequestParam("auditResultId") String auditResultId){
        return auditResultConverter.toDto(auditResultService.setAuditStatus(auditResultId, AuditStatus.ACTUAL));
    }

}
