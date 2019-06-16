package io.github.towerking.springbootmultimongodb.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Slf4j
public class MultiMongoProperties {

    @Bean(name = "firstMongoProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.data.mongodb.first")
    public MongoProperties firstMongoProperties() {
        log.info("first mongodb init");
        return new MongoProperties();
    }

    @Bean(name = "secondMongoProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.second")
    public MongoProperties secondMongoProperties() {
        log.info("second mongodb init");
        return new MongoProperties();
    }

}
