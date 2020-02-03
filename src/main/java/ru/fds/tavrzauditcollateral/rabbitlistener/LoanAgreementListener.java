package ru.fds.tavrzauditcollateral.rabbitlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.service.ObjectAuditService;


@Slf4j
@EnableRabbit
@Component
public class LoanAgreementListener {

    private final ObjectAuditService objectAuditService;

    public LoanAgreementListener(@Qualifier("loanAgreementService") ObjectAuditService objectAuditService) {
        this.objectAuditService = objectAuditService;
    }

    @RabbitListener(queues = "${queue_name.audit_new_loan_agreement}")
    public void auditNewLoanAgreement(Long loanAgreementId){
        log.debug("auditNewLoanAgreement. loanAgreementId: " + loanAgreementId);
        objectAuditService.doAuditAboutNewObject(loanAgreementId);
    }

    @RabbitListener(queues = "${queue_name.audit_exist_loan_agreement}")
    public void auditExistLoanAgreement(Long loanAgreementId){
        log.debug("auditExistLoanAgreement. loanAgreementId: " + loanAgreementId);
        objectAuditService.doAuditAboutExitObject(loanAgreementId);
    }
}
