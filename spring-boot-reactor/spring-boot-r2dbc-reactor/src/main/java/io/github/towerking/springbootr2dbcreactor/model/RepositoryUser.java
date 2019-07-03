package io.github.towerking.springbootr2dbcreactor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table("t_user")
public class RepositoryUser {
    @Id
    private Integer id;
    private String name;
    private Integer age;
    private Date createTime;
    private Date updateTime;
}
