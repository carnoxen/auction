package com.zerobase.endpoint.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.zerobase.domain.entity.*;
import com.zerobase.domain.repository.*;
import com.zerobase.endpoint.transfer.BidForm;
import com.zerobase.messaging.KafkaProperties;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BidService {
    private KafkaProperties kafkaProperties;
    private KafkaTemplate<String, Bid> kafkaTemplate;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private BidRepository bidRepository;

    public BidService(KafkaProperties kafkaProperties, 
                        KafkaTemplate<String, Bid> kafkaTemplate,
                        UserRepository userRepository,
                        ItemRepository itemRepository,
                        BidRepository bidRepository) {
        this.kafkaProperties = kafkaProperties;
        this.kafkaTemplate = kafkaTemplate;
        this.bidRepository = bidRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public void createBid(BidForm bidForm) {
        log.info("get user");
        User user = userRepository
            .findById(bidForm.getUser_id())
            .orElseThrow();
        log.info("get item");
        Item item = itemRepository
            .findById(bidForm.getItem_id())
            .orElseThrow();
        
            
        log.info("put all together to make a bid");
        Bid bid = bidForm.toEntity(user, item);
        kafkaTemplate.send(kafkaProperties.getTopic(), bid);
    }

    @KafkaListener(topics = "${kafka.topic}")
    public void saveBid(Bid bid){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            log.info("consumer start");
            Item item = bid.getItem();
            boolean valueIsGreater = item.getStart() < bid.getValue();
            boolean bidderIsNotOwner = item.getOwner().getId() != bid.getUser().getId();
            boolean availableInTimeout = bid.getCreated_at().getTime() <=
                (item.getUpdated_at().getTime() + (long) item.getTimeout());

            log.info("update item status");
            if (valueIsGreater && bidderIsNotOwner && availableInTimeout) {
                item.setStart(bid.getValue());
                item.setBidder(bid.getUser());
                itemRepository.save(item);
            }
        });
        executor.submit(() -> {
            log.info("save a bid");
            bidRepository.save(bid);
        });

        executor.shutdown();
    }
}
