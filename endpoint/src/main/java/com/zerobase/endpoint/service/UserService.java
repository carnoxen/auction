package com.zerobase.endpoint.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zerobase.domain.entity.User;
import com.zerobase.domain.repository.UserRepository;
import com.zerobase.endpoint.transfer.UserForm;

@Service
public class UserService {
    private UserRepository userRepository;

    public User createUser(UserForm userForm) {
        User user = userForm.toEntity();
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
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
