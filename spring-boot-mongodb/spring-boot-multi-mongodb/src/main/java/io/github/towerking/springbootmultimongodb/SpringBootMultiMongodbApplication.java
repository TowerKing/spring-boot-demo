package io.github.towerking.springbootmultimongodb;

import io.github.towerking.springbootmultimongodb.model.User;
import io.github.towerking.springbootmultimongodb.repository.first.FirstRepository;
import io.github.towerking.springbootmultimongodb.repository.second.SecondRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

@SpringBootApplication
@Slf4j
public class SpringBootMultiMongodbApplication implements CommandLineRunner {

    @Autowired
    private FirstRepository firstRepository;

    @Autowired
    private SecondRepository secondRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultiMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        crudByFirstRepository();
        crudBySecondRepository();
    }

    private void crudByFirstRepository() {
        User user = User.builder()
                .id(1)
                .name("tower king")
                .gender("male")
                .age(18).build();

        firstRepository.insert(user);
        log.info("-- Saved User {} --", firstRepository.findUserById(1));

        user.setAge(20);
        firstRepository.save(user);
        log.info("-- Updated User {} --", firstRepository.findUserById(1));

        firstRepository.delete(user);
        log.info("-- Saved User {} --", firstRepository.findUserById(1));
    }

    private void crudBySecondRepository() {
        User user = User.builder()
                .id(2)
                .name("tower king")
                .gender("male")
                .age(18).build();

        secondRepository.insert(user);
        log.info("-- second Saved User {} --", secondRepository.findUserById(2));

        user.setAge(20);
        secondRepository.save(user);
        log.info("-- second Updated User {} --", secondRepository.findUserById(2));

        secondRepository.delete(user);
        log.info("-- second Saved User {} --", secondRepository.findUserById(2));
    }
}
