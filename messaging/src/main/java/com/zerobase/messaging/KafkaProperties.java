package com.zerobase.messaging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("kafka")
public class KafkaProperties {
    private String topic;
    private Integer partition;
    private Integer replica;
}
