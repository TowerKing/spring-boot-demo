package io.github.towerking.springbootredisrepository.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash(value = "user", timeToLive = 60)
@Data
@Builder
public class UserCache {

    @Id
    private Integer id;
    @Indexed
    private String name;
    private String gender;
    private Integer age;
}
