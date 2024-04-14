package com.zerobase.endpoint.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zerobase.domain.entity.User;
import com.zerobase.endpoint.service.UserService;
import com.zerobase.endpoint.transfer.UserForm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
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
    public User postUser(@RequestBody UserForm entity) {
        return userService.createUser(entity);
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        var ouser = userService.findUserById(id);
        if (!ouser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ouser.get();
    }
    
    @PutMapping("/{id}")
    public User putUser(@PathVariable Long id, @RequestBody UserForm entity) {
        var ouser = userService.findUserById(id);
        if (!ouser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userService.editUser(entity, id);
    }
    
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return String.format("User id %d is deleted.", id);
    }
}
