package com.zerobase.endpoint.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerobase.transfer.request.BidRequest;

import lombok.extern.slf4j.Slf4j;

import com.zerobase.endpoint.service.BidService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/bid")
public class BidController {
    private BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public ResponseEntity<Message<?>> postBid(@RequestBody BidRequest bidForm) {
        bidService.createBid(bidForm);
        log.info("User {} bid an item {}.", bidForm.getUserId(), bidForm.getItemId());
        return ResponseEntity.ok(
            Message.builder()
                .message("Bid Complete")
                .build()
            );
    }
}
