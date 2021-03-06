package io.github.towerking.springbootrepositorymongodb.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Integer id;
    private String name;
    private String gender;
    private Integer age;
}
