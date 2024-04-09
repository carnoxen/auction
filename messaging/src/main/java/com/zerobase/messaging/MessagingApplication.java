package com.zerobase.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class MessagingApplication {
    @Autowired
    private KafkaProperties kafkaProperties;

    public static void main(String[] args) {
        SpringApplication.run(MessagingApplication.class, args);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(kafkaProperties.getTopic())
            .partitions(kafkaProperties.getPartition())
            .replicas(kafkaProperties.getReplica())
            .build();
    }
}
