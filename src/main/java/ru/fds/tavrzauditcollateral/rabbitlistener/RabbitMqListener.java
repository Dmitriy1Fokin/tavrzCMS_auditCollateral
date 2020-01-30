package ru.fds.tavrzauditcollateral.rabbitlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.dictionary.AuditLevel;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;
import ru.fds.tavrzauditcollateral.domain.AuditResult;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditLoanAgreement;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditResult;

import java.time.LocalDate;

@Slf4j
@EnableRabbit
@Component
public class RabbitMqListener {

    private final RepositoryAuditLoanAgreement repositoryAuditLoanAgreement;
    private final RepositoryAuditResult repositoryAuditResult;

    public RabbitMqListener(RepositoryAuditLoanAgreement repositoryAuditLoanAgreement,
                            RepositoryAuditResult repositoryAuditResult) {
        this.repositoryAuditLoanAgreement = repositoryAuditLoanAgreement;
        this.repositoryAuditResult = repositoryAuditResult;
    }

    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) {
        AuditResult auditResult = AuditResult.builder()
                .date(LocalDate.now())
                .typeOfObject(TypeOfObject.LOAN_AGREEMENT)
                .objectId(1L)
                .auditLevel(AuditLevel.LOW)
                .descriptionResult("описание")
                .fieldWithError("поле с ошибкой")
                .valueInField("значение в поле")
                .advice("совет")
                .auditStatus(AuditStatus.ACTUAL)
                .build();

        auditResult = repositoryAuditResult.save(auditResult);


        log.info("Received from queue 1: " + message);
        log.info("@@@@@" + repositoryAuditLoanAgreement.stringTest());
        log.info("!!!!!!! " + auditResult);
    }
}
