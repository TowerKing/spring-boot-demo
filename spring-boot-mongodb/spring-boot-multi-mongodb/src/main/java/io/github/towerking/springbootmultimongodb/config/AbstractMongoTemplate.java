package io.github.towerking.springbootmultimongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

public class AbstractMongoTemplate {

    MongoDbFactory createFactory(MongoProperties mongoProperties) {
        ServerAddress serverAddress = new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort());

        MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(),
                mongoProperties.getDatabase(), mongoProperties.getPassword());

        MongoClientOptions options = MongoClientOptions.builder()
                .serverSelectionTimeout(1000)
                .build();

        MongoClient mongoClient = new MongoClient(serverAddress, credential, options);
        return new SimpleMongoDbFactory(mongoClient, mongoProperties.getDatabase());
    }
}
