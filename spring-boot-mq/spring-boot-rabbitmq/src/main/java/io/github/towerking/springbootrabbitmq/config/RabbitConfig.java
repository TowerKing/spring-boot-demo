package io.github.towerking.springbootrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${queue.user.name}")
    private String queueName;

    @Bean
    public Queue userQueue() {
        return new Queue(queueName);
    }

}
