package com.zerobase.endpoint.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zerobase.domain.repository.UserRepository;
import com.zerobase.endpoint.request.UserRequest;
import com.zerobase.endpoint.response.ItemResponse;
import com.zerobase.endpoint.response.UserResponse;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userForm) {
        var user = userForm.toEntity();
        return UserResponse.toForm(userRepository.save(user));
    }

    public UserResponse findUserById(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        return UserResponse.toForm(user);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findItems(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        var items = user.getItems();
        return items.stream().map(ItemResponse::toForm).toList();
    }

    public UserResponse editUser(UserRequest userForm, Long id) {
        var user = userForm.toEntity();
        user.setId(id);
        return UserResponse.toForm(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
