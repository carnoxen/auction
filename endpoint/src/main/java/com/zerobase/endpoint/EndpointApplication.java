package com.zerobase.endpoint;

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
public class EndpointApplication {
    @Autowired
    private KafkaProperties kafkaProperties;
    
    public static void main(String[] args) {
        SpringApplication.run(EndpointApplication.class, args);
    }

    @Bean
    public NewTopic bidTopic() {
        return TopicBuilder.name(kafkaProperties.getTopic())
            .partitions(kafkaProperties.getPartition())
            .replicas(kafkaProperties.getReplica())
            .build();
    }
}
