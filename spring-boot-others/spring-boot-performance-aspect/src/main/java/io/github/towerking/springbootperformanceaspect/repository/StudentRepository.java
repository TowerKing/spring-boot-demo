package io.github.towerking.springbootperformanceaspect.repository;


import io.github.towerking.springbootperformanceaspect.model.Student;

import java.util.List;

public interface StudentRepository extends BaseRepository<Student, Long> {

    List<Student> findStudentsByStudentNameOrderById(String name);

}
