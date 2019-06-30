package io.github.towerking.springbootmybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.github.towerking.springbootmybatis.mapper.PageRowsMapper;
import io.github.towerking.springbootmybatis.mapper.StudentMapper;
import io.github.towerking.springbootmybatis.mapper.UserMapper;
import io.github.towerking.springbootmybatis.model.Student;
import io.github.towerking.springbootmybatis.model.StudentExample;
import io.github.towerking.springbootmybatis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
@MapperScan("io.github.towerking.springbootmybatis.mapper")
public class SpringBootMybatisApplication implements ApplicationRunner {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PageRowsMapper pageRowsMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // userCrud();
        // generateArtifacts();
        // studentCrud();
        page();
    }

    private void userCrud() {
        User user = User.builder().name("Hello").build();
        int count = userMapper.save(user);
        log.info("Save {} User {}", count, user);

        user = User.builder().name("world").build();
        count = userMapper.save(user);
        log.info("Save {} User {}", count, user);

        log.info("Find User {}", userMapper.findById(1));
    }

    // 用于 myBatis 生成
    private void generateArtifacts() throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                this.getClass().getResourceAsStream("/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    private void studentCrud() {
        Student hello = new Student()
                .withStudentName("hello")
                .withStudentNo("001");
        studentMapper.insert(hello);

        Student world = new Student()
                .withStudentName("world")
                .withStudentNo("002");
        studentMapper.insert(world);

        log.info("Student 1: {}", studentMapper.selectByPrimaryKey(1));

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStudentNameEqualTo("world");
        studentMapper.selectByExample(studentExample).forEach(s -> {
            log.info("select by example world: {}", s);
        });

    }

    private void page() {
        // user RowBounds 物理分页
        pageRowsMapper.findAllWithRowBounds(new RowBounds(0, 2)).forEach(row -> log.info("page: {}", row));

        log.info("------------------------------------------------");

        // use params
        pageRowsMapper.findAllWithParams(1, 4).forEach(row -> log.info("row: {}", row));
        @SuppressWarnings("unchecked")
        PageInfo pageInfo = new PageInfo(pageRowsMapper.findAllWithParams(2,4));
        log.info("pageInfo {}", pageInfo);

        // use PageHelper with startPage
        log.info("use PageHelper with startPage");
        PageHelper.startPage(1, 4);
        pageRowsMapper.findAll().forEach(row -> log.info("row {}", row));

        // use PageHelper with offsetPage
        log.info("use PageHelper with offsetPage");
        PageHelper.offsetPage(1, 4);
        pageRowsMapper.findAll().forEach(row -> log.info("row {}", row));

        // more use methods, see link:
        // https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
    }
}
