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
public class PledgeAgreementListener {

    private final ObjectAuditService pledgeAgreementService;

    public PledgeAgreementListener(@Qualifier("pledgeAgreementService") ObjectAuditService pledgeAgreementService) {
        this.pledgeAgreementService = pledgeAgreementService;
    }

    @RabbitListener(queues = "${queue_name.audit_new_pledge_agreement}")
    public void auditNewPledgeAgreement(Long pledgeAgreementId){
        log.debug("auditNewPledgeAgreement. pledgeAgreementId: " + pledgeAgreementId);
        pledgeAgreementService.doAuditAboutNewObject(pledgeAgreementId);
    }

    @RabbitListener(queues = "${queue_name.audit_exist_pledge_agreement}")
    public void auditExistPledgeAgreement(Long pledgeAgreementId){
        log.debug("auditExistPledgeAgreement. pledgeAgreementId: " + pledgeAgreementId);
        pledgeAgreementService.doAuditAboutExitObject(pledgeAgreementId);
    }
}