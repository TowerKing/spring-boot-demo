package io.github.towerking.springbootmultimongodb.repository.second;

import io.github.towerking.springbootmultimongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondRepository extends MongoRepository<User, Integer> {
    User findUserById(Integer id);
}
