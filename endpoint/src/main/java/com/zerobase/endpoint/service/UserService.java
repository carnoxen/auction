package com.zerobase.endpoint.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.NotAcceptableStatusException;

import com.zerobase.domain.repository.UserRepository;
import com.zerobase.transfer.request.UserRequest;
import com.zerobase.transfer.response.ItemResponse;
import com.zerobase.transfer.response.UserResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userForm) {
        var user = userForm.toEntity();
        if (userRepository.findByUsername(userForm.getUsername()).isPresent()) {
            log.error("Username {} already exists", user.getUsername());
            throw new NotAcceptableStatusException("username already exists");
        }

        return UserResponse.toForm(userRepository.save(user));
    }

    public UserResponse findUserById(Long id) throws NotFoundException {
        var opUser = userRepository.findById(id);
        if (!opUser.isPresent()) {
            throw new NotFoundException();
        }

        return UserResponse.toForm(opUser.get());
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findItems(Long id) throws NotFoundException {
        var opUser = userRepository.findById(id);
        if (!opUser.isPresent()) {
            throw new NotFoundException();
        }

        var items = opUser.get().getItems();
        return items.stream().map(ItemResponse::toForm).toList();
    }

    public UserResponse editUser(UserRequest userForm, Long id) throws NotFoundException {
        if (!userRepository.findById(id).isPresent()) {
            throw new NotFoundException();
        }
        
        var user = userForm.toEntity();
        user.setId(id);
        return UserResponse.toForm(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
