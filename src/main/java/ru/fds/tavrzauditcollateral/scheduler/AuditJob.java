package ru.fds.tavrzauditcollateral.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.service.AuditResultService;

@Slf4j
@Component
public class AuditJob {

    private final AuditResultService auditResultService;

    public AuditJob(AuditResultService auditResultService) {
        this.auditResultService = auditResultService;
    }

    @Scheduled(cron = "${cron.expression}")
    public void startJob(){
        log.info("Start executeAuditInAllObjects");
        auditResultService.executeAuditInAllObjects();
        log.info("End executeAuditInAllObjects");
    }
}
