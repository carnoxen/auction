package com.zerobase.consumer;

import java.util.List;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.config.TopicBuilder;

import com.zerobase.messaging.KafkaProperties;

@SpringBootApplication(scanBasePackages = {"com.zerobase"})
@EnableJpaRepositories("com.zerobase.domain.repository")
@EntityScan("com.zerobase.domain.entity")
public class ConsumerApplication {
    @Autowired
    private KafkaProperties kafkaProperties;
    
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public List<NewTopic> bidTopic() {
        return kafkaProperties.getTopics().entrySet().stream()
            .map(x -> TopicBuilder.name(x.getKey())
            .partitions(x.getValue().getPartition())
            .replicas(x.getValue().getReplica())
            .build()).toList();
    }
}
