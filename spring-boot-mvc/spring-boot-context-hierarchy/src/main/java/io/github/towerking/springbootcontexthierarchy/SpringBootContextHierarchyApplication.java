package io.github.towerking.springbootcontexthierarchy;

import io.github.towerking.springbootcontexthierarchy.context.HelloBean;
import io.github.towerking.springbootcontexthierarchy.foo.FooConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringBootContextHierarchyApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootContextHierarchyApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ApplicationContext fooContext = new AnnotationConfigApplicationContext(FooConfig.class);
        HelloBean bean = fooContext.getBean("helloBeanOne", HelloBean.class);
        bean.hello();

        // 没有增强，why ?
        ClassPathXmlApplicationContext barContext = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"}, fooContext);
        bean = barContext.getBean("helloBeanOne", HelloBean.class);
        bean.hello();

        // 有增强， why ?
        bean = barContext.getBean("helloBeanTwo", HelloBean.class);
        bean.hello();
    }
}
