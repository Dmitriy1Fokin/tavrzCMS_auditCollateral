package ru.fds.tavrzauditcollateral.rabbitlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.service.AuditResultService;

@Slf4j
@EnableRabbit
@Component
public class AuditListener {

    private final AuditResultService auditResultService;

    public AuditListener(AuditResultService auditResultService) {
        this.auditResultService = auditResultService;
    }

    @RabbitListener(queues = "${queue_name.execute_audit}")
    public void executeAudit(){
        auditResultService.executeAuditInAllObjects();
    }
}
