package com.zerobase.endpoint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zerobase.domain.entity.Item;
import com.zerobase.domain.entity.User;
import com.zerobase.domain.repository.UserRepository;
import com.zerobase.endpoint.transfer.UserForm;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserForm userForm) {
        User user = userForm.toEntity();
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<Item> findItems(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getItems();
    }

    public User editUser(UserForm userForm, Long id) {
        User user = userForm.toEntity();
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
