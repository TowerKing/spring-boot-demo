package io.github.towerking.springbootr2dbcreactor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Integer id;
    private String name;
    private Integer age;
    private Date createTime;
    private Date updateTime;

}
