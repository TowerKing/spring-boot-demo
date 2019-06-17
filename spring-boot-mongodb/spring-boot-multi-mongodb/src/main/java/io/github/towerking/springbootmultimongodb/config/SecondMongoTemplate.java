package io.github.towerking.springbootmultimongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "io.github.towerking.springbootmultimongodb.repository.second", mongoTemplateRef = "secondMongo")
public class SecondMongoTemplate extends AbstractMongoTemplate {

    @Autowired
    @Qualifier("secondMongoProperties")
    private MongoProperties mongoProperties;

    @Bean(name = "secondMongo")
    public MongoTemplate secondMongoTemplate() {
        return new MongoTemplate(createFactory(this.mongoProperties));
    }

}
