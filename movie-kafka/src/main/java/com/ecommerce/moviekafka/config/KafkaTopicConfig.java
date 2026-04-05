package com.ecommerce.moviekafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic movieEventsTopic() {
        return TopicBuilder.name("movie.events")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic actorEventsTopic() {
        return TopicBuilder.name("actor.events")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
