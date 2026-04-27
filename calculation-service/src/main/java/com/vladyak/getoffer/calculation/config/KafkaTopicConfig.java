package com.vladyak.getoffer.calculation.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.calculation-completed}")
    private String calculationCompletedTopic;

    @Bean
    public NewTopic calculationCompletedTopic() {
        return TopicBuilder.name(calculationCompletedTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
