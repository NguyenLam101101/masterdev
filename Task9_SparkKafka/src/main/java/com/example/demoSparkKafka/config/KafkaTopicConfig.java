package com.example.demoSparkKafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic Topic(){
        return TopicBuilder
                .name("data_tracking_lamnv155")
                .partitions(2)
                .replicas(2)
                .build();
    }
}
