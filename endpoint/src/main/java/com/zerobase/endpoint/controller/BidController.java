package com.zerobase.endpoint.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerobase.domain.entity.Bid;
import com.zerobase.endpoint.form.BidForm;
import com.zerobase.endpoint.service.BidService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/bid")
public class BidController {
    private BidService bidService;

    @PostMapping
    public Bid postBid(@RequestBody BidForm bidForm) {
        return bidService.createBid(bidForm);
    }
}
