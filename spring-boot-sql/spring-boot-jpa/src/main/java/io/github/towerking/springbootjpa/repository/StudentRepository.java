package io.github.towerking.springbootjpa.repository;

import io.github.towerking.springbootjpa.model.Student;

import java.util.List;

public interface StudentRepository extends BaseRepository<Student, Long> {

    List<Student> findStudentsByStudentNameOrderById(String name);

}
