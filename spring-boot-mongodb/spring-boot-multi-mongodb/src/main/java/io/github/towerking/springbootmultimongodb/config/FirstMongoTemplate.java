package io.github.towerking.springbootmultimongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "io.github.towerking.springbootmultimongodb.repository.first", mongoTemplateRef = "firstMongo")
public class FirstMongoTemplate extends AbstractMongoTemplate {

    @Autowired
    @Qualifier("firstMongoProperties")
    private MongoProperties mongoProperties;

    @Primary
    @Bean(name = "firstMongo")
    public MongoTemplate firstMongoTemplate() {
        return new MongoTemplate(createFactory(this.mongoProperties));
    }

}
