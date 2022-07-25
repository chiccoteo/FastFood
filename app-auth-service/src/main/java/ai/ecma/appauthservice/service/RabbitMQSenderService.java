package ai.ecma.appauthservice.service;

import ai.ecma.library.payload.RabbitMQMessageTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQSenderService {
    private final RabbitTemplate rabbitTemplate;

    public void send(RabbitMQMessageTest rabbitMQMessageTest){
        log.info("Start queueing: {}",rabbitMQMessageTest);
        rabbitTemplate.convertAndSend("B5DirectExchange","B5RoutineKey",rabbitMQMessageTest);
        log.info("End queueing: {}",rabbitMQMessageTest);
    }

}
