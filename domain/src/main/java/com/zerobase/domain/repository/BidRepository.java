package com.zerobase.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.zerobase.domain.entity.Bid;

public interface BidRepository extends CrudRepository<Bid, Long> {
    
}
