package io.github.towerking.springbootmultimongodb.config;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "io.github.towerking.springbootmultimongodb.repository.first", mongoTemplateRef = "firstMongo")
public class FirstMongoTemplate {

    @Autowired
    @Qualifier("firstMongoProperties")
    private MongoProperties mongoProperties;

    @Primary
    @Bean(name = "firstMongo")
    public MongoTemplate firstMongoTemplate() {
        return new MongoTemplate(firstFactory(this.mongoProperties));
    }

    @Bean
    @Primary
    public MongoDbFactory firstFactory(MongoProperties mongoProperties) {
        ServerAddress serverAddress = new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort());

        MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(),
                mongoProperties.getDatabase(), mongoProperties.getPassword());

        MongoClientOptions options = MongoClientOptions.builder()
                .serverSelectionTimeout(1000)
                .build();

        MongoClient mongoClient = new MongoClient(serverAddress, credential, options);
        return new SimpleMongoDbFactory(mongoClient, mongoProperties.getDatabase());

//        ServerAddress serverAddress = new ServerAddress(mongoProperties.getUri());
//        return new SimpleMongoDbFactory(new MongoClient(serverAddress), mongoProperties.getDatabase());
    }

}
