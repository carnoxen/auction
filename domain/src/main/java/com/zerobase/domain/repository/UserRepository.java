package com.zerobase.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.zerobase.domain.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
