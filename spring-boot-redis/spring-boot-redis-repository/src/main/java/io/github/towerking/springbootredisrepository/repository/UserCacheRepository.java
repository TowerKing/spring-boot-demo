package io.github.towerking.springbootredisrepository.repository;

import io.github.towerking.springbootredisrepository.model.UserCache;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCacheRepository extends CrudRepository<UserCache, Integer> {

    Optional<UserCache> findUserCacheByName(String name);
}
