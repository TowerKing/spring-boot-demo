package io.github.towerking.springbootsinglemongodb;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.github.towerking.springbootsinglemongodb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@SpringBootApplication
@Slf4j
public class SpringBootSingleMongodbApplication implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSingleMongodbApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveUser();
        updateUser();
        findUser();
        removerUser();
    }

    private void saveUser() {
        User user = User.builder()
                .id(1)
                .name("tower king")
                .gender("Male")
                .age(18)
                .build();

        User saved = mongoTemplate.save(user);
        log.info("User {}", saved);
    }

    private void updateUser() {
        UpdateResult updateResult = mongoTemplate.updateFirst(query(), new Update().set("age", 20), User.class);

        log.info("Update Result: {}", updateResult.getModifiedCount());
    }

    private void findUser() {
        List<User> list = mongoTemplate.find(query(), User.class);
        list.forEach(u -> log.info("User {}", u));
    }

    private void removerUser() {
        DeleteResult result = mongoTemplate.remove(query(), User.class);
        log.info("Delete Result {}", result.getDeletedCount());
    }

    private Query query() {
        return Query.query(Criteria.where("id").is(1));
    }

}
