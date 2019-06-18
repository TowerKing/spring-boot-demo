package io.github.towerking.springbootredis.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {

    private Integer id;
    private String name;
    private String gender;
    private Integer age;
}
