package org.walavo.bar.generate.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.walavo.bar.generate.model.repository.BarcodeRepository;

@EnableReactiveMongoRepositories(basePackageClasses = BarcodeRepository.class)
public class MongoReactiveConfig extends AbstractReactiveMongoConfiguration {
    @Bean
    public MongoClient mongoClient() {
        return MongoClients
                .create();
    }

    @Override
    protected String getDatabaseName() {
        return "barcodes";
    }

    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory() {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient(), getDatabaseName());
    }

}
