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
public class PledgeSubjectListener {

    private final ObjectAuditService pledgeSubjectService;

    public PledgeSubjectListener(@Qualifier("pledgeSubjectService") ObjectAuditService pledgeSubjectService) {
        this.pledgeSubjectService = pledgeSubjectService;
    }

    @RabbitListener(queues = "${queue_name.audit_new_pledge_subject}")
    public void auditNewPledgeSubject(Long pledgeSubjectId){
        log.debug("auditNewPledgeSubject. pledgeSubjectId: " + pledgeSubjectId);
        pledgeSubjectService.doAuditAboutNewObject(pledgeSubjectId);
    }

    @RabbitListener(queues = "${queue_name.audit_exist_pledge_subject}")
    public void auditExistPledgeSubject(Long pledgeSubjectId){
        log.debug("auditExistPledgeSubject. pledgeSubjectId: " + pledgeSubjectId);
        pledgeSubjectService.doAuditAboutExitObject(pledgeSubjectId);
    }
}
