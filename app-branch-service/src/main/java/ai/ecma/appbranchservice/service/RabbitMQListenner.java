package ai.ecma.appbranchservice.service;

import ai.ecma.library.payload.RabbitMQMessageTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQListenner {
    @RabbitListener(queues = "B5Queue")
    public void listen(RabbitMQMessageTest rabbitMQMessageTest) {
        log.info("Receive Queue Message: {}", rabbitMQMessageTest);
    }
}
