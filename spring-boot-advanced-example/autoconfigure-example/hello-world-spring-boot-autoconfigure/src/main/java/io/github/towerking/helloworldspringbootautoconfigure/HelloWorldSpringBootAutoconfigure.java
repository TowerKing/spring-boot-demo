package io.github.towerking.helloworldspringbootautoconfigure;

import io.github.towerking.springboothelloworld.HelloWorldApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(HelloWorldApplicationRunner.class)
public class HelloWorldSpringBootAutoconfigure {

    @Bean
    @ConditionalOnMissingBean(HelloWorldApplicationRunner.class)
    @ConditionalOnProperty(name = "hello.world.enable", havingValue = "true", matchIfMissing = true)
    public HelloWorldApplicationRunner helloWorldApplicationRunner() {
        return new HelloWorldApplicationRunner();
    }

}
