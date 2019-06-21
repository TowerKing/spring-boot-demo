package io.github.towerking.springbootrabbitmq.producer;

import io.github.towerking.springbootrabbitmq.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class UserSender {

    @Autowired
    private Queue userQueue;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendUser(User user) {
        rabbitTemplate.convertAndSend(userQueue.getName(), user);
    }

}
