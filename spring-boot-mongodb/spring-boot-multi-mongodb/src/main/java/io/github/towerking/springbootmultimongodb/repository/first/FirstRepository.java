package io.github.towerking.springbootmultimongodb.repository.first;

import io.github.towerking.springbootmultimongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FirstRepository extends MongoRepository<User, Integer> {
    User findUserById(Integer id);
}
