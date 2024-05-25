package com.zerobase.endpoint.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.zerobase.transfer.request.BidRequest;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BidService {
    private final String bidTopic = System.getProperty("bid_topic");
    private KafkaTemplate<String, BidRequest> kafkaTemplate;

    public BidService(
        KafkaTemplate<String, BidRequest> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createBid(BidRequest bidForm) {
        log.info("send a request to kafka to make a bid");
        kafkaTemplate.send(bidTopic, bidForm);
    }
}
