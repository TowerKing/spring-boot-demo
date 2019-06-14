package io.github.towerking.springbootrepositorymongodb.repository;

import io.github.towerking.springbootrepositorymongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {

    User findUserById(Integer id);
}
