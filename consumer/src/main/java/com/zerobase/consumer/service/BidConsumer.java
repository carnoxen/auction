package com.zerobase.consumer.service;

import java.util.Objects;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.zerobase.domain.entity.*;
import com.zerobase.domain.repository.*;
import com.zerobase.transfer.request.BidRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BidConsumer {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final BidRepository bidRepository;

    public BidConsumer(
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

        log.info("update item start");
        boolean isValueGreaterThanItem = item.getStart() < bidForm.getValue();
        boolean isBidderNotOwner = !Objects.equals(item.getOwner().getId(), bidForm.getUserId());
        boolean availableInTimeout = bidForm.getCreatedAt().getTime() <=
                (item.getUpdatedAt().getTime() + (long) item.getTimeout() * 1000L);

        if (isValueGreaterThanItem && isBidderNotOwner && availableInTimeout) {
            log.info("item update succeeded");
            item.setStart(bidForm.getValue());
            item.setBidder(bidder);
            itemRepository.save(item);
        }

        log.info("save a bid");
        Bid bid = bidForm.toEntity(bidder, item);
        bidRepository.save(bid);
    }
}
