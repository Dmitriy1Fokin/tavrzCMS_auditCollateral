package ru.fds.tavrzauditcollateral.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.tavrzauditcollateral.service.AuditResultService;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;

@RestController
@RequestMapping("/audit")
public class AuditResultController {

    private final ObjectAuditService loanAgreementService;
    private final ObjectAuditService pledgeAgreementService;
    private final ObjectAuditService pledgeSubjectService;
    private final AuditResultService auditResultService;

    public AuditResultController(@Qualifier("loanAgreementService") ObjectAuditService loanAgreementService,
                                 @Qualifier("pledgeAgreementService") ObjectAuditService pledgeAgreementService,
                                 @Qualifier("pledgeSubjectService") ObjectAuditService pledgeSubjectService,
                                 AuditResultService auditResultService) {
        this.loanAgreementService = loanAgreementService;
        this.pledgeAgreementService = pledgeAgreementService;
        this.pledgeSubjectService = pledgeSubjectService;
        this.auditResultService = auditResultService;
    }


}
