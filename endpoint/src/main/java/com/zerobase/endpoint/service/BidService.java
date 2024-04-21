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

    public Bid createBid(BidForm bidForm) {
        User user = userRepository.findById(bidForm.getUser_id()).orElseThrow();
        Item item = itemRepository.findById(bidForm.getItem_id()).orElseThrow();
        Bid bid = bidForm.toEntity(user, item);
        kafkaTemplate.send(kafkaProperties.getTopic(), bid);
        return bid;
    }

    @KafkaListener(topics = "${kafka.topic}")
    public void saveBid(Bid bid){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            Item item = bid.getItem();
            if (item.getStart() < bid.getValue()) {
                item.setStart(bid.getValue());
                item.setBidder(bid.getUser());
                itemRepository.save(item);
            }
        });
        executor.submit(() -> {
            bidRepository.save(bid);
        });

        executor.shutdown();
    }
}
