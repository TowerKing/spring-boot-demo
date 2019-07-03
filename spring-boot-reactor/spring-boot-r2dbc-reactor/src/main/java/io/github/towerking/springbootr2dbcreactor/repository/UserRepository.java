package io.github.towerking.springbootr2dbcreactor.repository;

import io.github.towerking.springbootr2dbcreactor.model.RepositoryUser;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<RepositoryUser, Integer> {

    @Query("select * from t_user where name = $1")
    Flux<RepositoryUser> findByName(String name);
}
