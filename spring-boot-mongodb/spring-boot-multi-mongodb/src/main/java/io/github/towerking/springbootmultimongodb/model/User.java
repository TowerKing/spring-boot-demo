package io.github.towerking.springbootmultimongodb.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "user")
public class User {

    private Integer id;
    private String name;
    private String gender;
    private Integer age;
}
