package com.zerobase.endpoint.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerobase.endpoint.request.BidRequest;
import com.zerobase.endpoint.service.BidService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/bid")
public class BidController {
    private BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public String postBid(@RequestBody BidRequest bidForm) {
        bidService.createBid(bidForm);
        return String.format(
            "User %d bid an item %d.", 
            bidForm.getUser_id(), 
            bidForm.getItem_id()
        );
    }
}
