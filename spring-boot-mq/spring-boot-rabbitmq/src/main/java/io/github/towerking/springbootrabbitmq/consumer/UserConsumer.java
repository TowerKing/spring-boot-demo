package io.github.towerking.springbootrabbitmq.consumer;

import io.github.towerking.springbootrabbitmq.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserConsumer {

    @RabbitListener(queues = "${queue.user.name}")
    public void getUser(User user) {
        log.info("Consumer user: {}", user);
    }
}
