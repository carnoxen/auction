package com.zerobase.endpoint.service;

import java.util.concurrent.Executors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.zerobase.domain.entity.*;
import com.zerobase.domain.repository.*;
import com.zerobase.endpoint.request.BidRequest;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BidService {
    private final String bidTopic = System.getProperty("bid_topic");
    private KafkaTemplate<String, BidRequest> kafkaTemplate;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private BidRepository bidRepository;

    public BidService(
        KafkaTemplate<String, BidRequest> kafkaTemplate,
        UserRepository userRepository,
        ItemRepository itemRepository,
        BidRepository bidRepository
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.bidRepository = bidRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public void createBid(BidRequest bidForm) {
        log.info("send a request to kafka to make a bid");
        kafkaTemplate.send(bidTopic, bidForm);
    }

    @KafkaListener(topics = "${bid_topic}")
    public void saveBid(BidRequest bidForm){
        Item item = itemRepository
            .findById(bidForm.getItem_id())
            .orElseThrow();
        User bidder = userRepository
            .findById(bidForm.getUser_id())
            .orElseThrow();

        var executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            log.info("update item start");
            boolean isValueGreaterThanItem = item.getStart() < bidForm.getValue();
            boolean isBidderNotOwner = item.getOwner().getId() != bidForm.getUser_id();
            boolean availableInTimeout = bidForm.getCreated_at().getTime() <=
                (item.getUpdated_at().getTime() + (long) item.getTimeout() * 1000l);

            if (isValueGreaterThanItem && isBidderNotOwner && availableInTimeout) {
                log.info("item update succeeded");
                item.setStart(bidForm.getValue());
                item.setBidder(bidder);
                itemRepository.save(item);
            }
        });
        executor.submit(() -> {
            log.info("save a bid");
            Bid bid = bidForm.toEntity(bidder, item);
            bidRepository.save(bid);
        });

        executor.shutdown();
    }
}
