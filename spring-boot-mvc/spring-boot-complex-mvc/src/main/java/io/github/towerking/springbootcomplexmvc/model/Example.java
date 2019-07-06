package io.github.towerking.springbootcomplexmvc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class Example {

    private Integer id;
    @NotEmpty
    private String name;
    @NotNull
    private Date createTime;
}
