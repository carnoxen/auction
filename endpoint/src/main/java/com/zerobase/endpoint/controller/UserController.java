package com.zerobase.endpoint.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerobase.endpoint.request.UserRequest;
import com.zerobase.endpoint.response.ItemResponse;
import com.zerobase.endpoint.response.UserResponse;
import com.zerobase.endpoint.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse postUser(@RequestBody UserRequest entity) {
        var userRes = userService.createUser(entity);
        return userRes;
    }
    
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        var userRes = userService.findUserById(id);
        return userRes;
    }
    
    @GetMapping("/{id}/items")
    public List<ItemResponse> getUserItems(@PathVariable Long id) {
        var list = userService.findItems(id);
        return list;
    }
    
    @PutMapping("/{id}")
    public UserResponse putUser(@PathVariable Long id, @RequestBody UserRequest entity) {
        var userRes = userService.editUser(entity, id);
        return userRes;
    }
    
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return String.format("User id %d is deleted.", id);
    }
}
