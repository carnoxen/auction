package com.zerobase.consumer.service;

import java.util.concurrent.Executors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.zerobase.domain.entity.*;
import com.zerobase.domain.repository.*;
import com.zerobase.transfer.request.BidRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BidService {
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private BidRepository bidRepository;

    public BidService(
        UserRepository userRepository,
        ItemRepository itemRepository,
        BidRepository bidRepository
    ) {
        this.bidRepository = bidRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "${bid_topic}")
    public void saveBid(BidRequest bidForm){
        Item item = itemRepository
            .findById(bidForm.getItemId())
            .orElseThrow();
        User bidder = userRepository
            .findById(bidForm.getUserId())
            .orElseThrow();

        var executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            log.info("update item start");
            boolean isValueGreaterThanItem = item.getStart() < bidForm.getValue();
            boolean isBidderNotOwner = item.getOwner().getId() != bidForm.getUserId();
            boolean availableInTimeout = bidForm.getCreatedAt().getTime() <=
                (item.getUpdatedAt().getTime() + (long) item.getTimeout() * 1000l);

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
