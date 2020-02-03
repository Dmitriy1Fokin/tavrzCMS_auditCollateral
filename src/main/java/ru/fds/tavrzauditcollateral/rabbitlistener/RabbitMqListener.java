package ru.fds.tavrzauditcollateral.rabbitlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.service.LoanAgreementService;


@Slf4j
@EnableRabbit
@Component
public class RabbitMqListener {

    private final LoanAgreementService loanAgreementService;

    public RabbitMqListener(LoanAgreementService loanAgreementService) {
        this.loanAgreementService = loanAgreementService;
    }

    @RabbitListener(queues = "queueAuditNewLoanAgreement")
    public void auditNewLoanAgreement(Long loanAgreementId){
        loanAgreementService.doAuditResultAboutNewLoanAgreement(loanAgreementId);
    }
}
