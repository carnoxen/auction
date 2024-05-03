package com.zerobase.messaging;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("kafka")
public class KafkaProperties {
    private Map<String, Topic> topics = new HashMap<>();
}
