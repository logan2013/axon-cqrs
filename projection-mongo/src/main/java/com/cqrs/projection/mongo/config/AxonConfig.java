package com.cqrs.projection.mongo.config;

import com.mongodb.MongoClient;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {

    @Bean
    @Primary
    public Serializer serializer() {

        return new JacksonSerializer.Builder().build();
    }

    @Bean
    public SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean() {

        return new SpringAggregateSnapshotterFactoryBean();
    }

    @Bean
    public TokenStore tokenStore(MongoClient mongoClient) {
        return MongoTokenStore.builder()
                .serializer(JacksonSerializer.builder().build())
                .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(mongoClient).build())
                .build();
    }


}
