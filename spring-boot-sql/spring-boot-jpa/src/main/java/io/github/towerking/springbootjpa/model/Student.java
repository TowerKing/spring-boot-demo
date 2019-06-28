package io.github.towerking.springbootjpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String studentNo;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    private String studentName;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @CreationTimestamp
    private Date updateTime;

}
