package io.github.towerking.springbootrepositorymongodb;

import io.github.towerking.springbootrepositorymongodb.model.User;
import io.github.towerking.springbootrepositorymongodb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@SpringBootApplication
@EnableMongoRepositories
public class SpringBootRepositoryMongodbApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRepositoryMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .id(1)
                .name("tower king")
                .gender("male")
                .age(18).build();

        userRepository.insert(user);
        log.info("-- Saved User {} --", userRepository.findUserById(1));

        user.setAge(20);
        userRepository.save(user);
        log.info("-- Updated User {} --", userRepository.findUserById(1));

        userRepository.delete(user);
        log.info("-- Saved User {} --", userRepository.findUserById(1));
    }
}
