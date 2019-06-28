package io.github.towerking.springbootjpa;

import io.github.towerking.springbootjpa.model.Student;
import io.github.towerking.springbootjpa.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class SpringBootJpaApplication implements ApplicationRunner {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initStudent();
        findStudents();
    }

    private void initStudent() {
        Student student = Student.builder().studentNo("001")
                .studentName("Hello")
                .grade("大一")
                .build();

        studentRepository.save(student);
        log.info("student: {}", student);

        student = Student.builder().studentNo("002")
                .studentName("World")
                .grade("大二")
                .build();
        studentRepository.save(student);
        log.info("student: {}", student);
    }

    private void findStudents() {
        studentRepository.findAll().forEach(student -> {
            log.info("student: {}", student);
        });

        log.info("find student by name");
        studentRepository.findStudentsByStudentNameOrderById("Hello").forEach(student -> {
            log.info("student {}", student);
        });
    }
}
