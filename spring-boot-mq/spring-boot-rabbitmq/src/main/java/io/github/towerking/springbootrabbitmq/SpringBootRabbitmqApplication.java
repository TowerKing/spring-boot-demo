package io.github.towerking.springbootrabbitmq;

import io.github.towerking.springbootrabbitmq.model.User;
import io.github.towerking.springbootrabbitmq.producer.UserSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRabbitmqApplication implements ApplicationRunner {

    @Autowired
    private UserSender userSender;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRabbitmqApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendUser();
    }

    private void sendUser() {
        User user = User.builder()
                .id(1)
                .name("tower king")
                .age(20)
                .build();
        userSender.sendUser(user);
    }
}
