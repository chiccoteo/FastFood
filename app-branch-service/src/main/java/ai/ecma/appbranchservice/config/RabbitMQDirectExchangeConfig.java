package ai.ecma.appbranchservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirectExchangeConfig {
    @Bean
    DirectExchange exchange(){
        return new DirectExchange("B5DirectExchange");
    }

//    @Bean
//    TopicExchange exchange(){
//        return new TopicExchange("B5TopicExchange");
//    }

    @Bean
    Queue queue(){
        return new Queue("B5Queue",false);
    }
    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with("B5RoutineKey");
    }

}
