package ru.fds.tavrzauditcollateral.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Value("${spring.rabbitmq.host}")
    private String hostName;
    @Value("${queue_name.audit_new_loan_agreement}")
    private String queueNameAuditNewLoanAgreement;
    @Value("${queue_name.audit_exist_loan_agreement}")
    private String queueNameAuditExistLoanAgreement;
    @Value("${queue_name.audit_new_pledge_agreement}")
    private String queueNameAuditNewPledgeAgreement;
    @Value("${queue_name.audit_exist_pledge_agreement}")
    private String queueNameAuditExistPledgeAgreement;
    @Value("${queue_name.audit_new_pledge_subject}")
    private String queueNameAuditNewPledgeSubject;
    @Value("${queue_name.audit_exist_pledge_subject}")
    private String queueNameAuditExistPledgeSubject;


    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(hostName);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue queueAuditNewLoanAgreement(){
        return new Queue(queueNameAuditNewLoanAgreement);
    }

    @Bean
    public Queue queueAuditExistLoanAgreement(){
        return new Queue(queueNameAuditExistLoanAgreement);
    }

    @Bean
    public Queue queueAuditNewPledgeAgreement(){
        return new Queue(queueNameAuditNewLoanAgreement);
    }

    @Bean
    public Queue queueAuditExistPledgeAgreement(){
        return new Queue(queueNameAuditExistPledgeAgreement);
    }

    @Bean
    public Queue queueAuditNewPledgeSubject(){
        return new Queue(queueNameAuditNewPledgeSubject);
    }

    @Bean
    public Queue queueAuditExistPledgeSubject(){
        return new Queue(queueNameAuditExistPledgeSubject);
    }

}
