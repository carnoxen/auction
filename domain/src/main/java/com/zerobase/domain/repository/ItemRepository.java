package com.zerobase.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.zerobase.domain.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
    
}
