package com.zerobase.endpoint.service;

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

    public Bid createBid(BidForm bidForm) {
        User user = userRepository.findById(bidForm.getUser_id()).orElseThrow();
        Item item = itemRepository.findById(bidForm.getItem_id()).orElseThrow();
        Bid bid = bidForm.toEntity(user, item);
        kafkaTemplate.send(kafkaProperties.getTopic(), bid);
        return bid;
    }

    @KafkaListener(topics = "${kafka.topic}")
    public void saveBid(Bid bid){
        Item item = bid.getItem();
        if (item.getStart() < bid.getValue()) {
            item.setStart(bid.getValue());
            item.setBidder(bid.getUser());
            itemRepository.save(item);
        }
        
        bidRepository.save(bid);
    }
}
