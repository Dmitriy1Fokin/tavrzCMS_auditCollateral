package ru.fds.tavrzauditcollateral.rabbitlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@EnableRabbit
@Component
public class RabbitMqListener {

    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) {
        log.info("Received from queue 1: " + message);
    }
}
