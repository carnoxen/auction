package com.zerobase.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.zerobase.domain.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
