package io.github.towerking.springbootcontexthierarchy.foo;

import io.github.towerking.springbootcontexthierarchy.context.HelloBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class FooConfig {

    @Bean
    public HelloBean helloBeanOne() {
        return new HelloBean("World");
    }

    @Bean
    public HelloBean helloBeanTwo() {
        return new HelloBean("World again");
    }

    @Bean
    public FooAspect fooAspect() {
        return new FooAspect();
    }

}
