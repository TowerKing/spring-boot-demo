package io.github.towerking.springbootmybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRows implements Serializable {

    private Integer id;
    private String title;
    private Date createTime;
    private Date updateTime;
}
