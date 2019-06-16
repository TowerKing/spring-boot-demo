package io.github.towerking.springbootmultimongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
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

import java.util.Arrays;

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
        MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(),
                mongoProperties.getDatabase(), mongoProperties.getPassword());
        ServerAddress serverAddress = new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort());

        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        return new SimpleMongoDbFactory(mongoClient, mongoProperties.getDatabase());
    }

}
