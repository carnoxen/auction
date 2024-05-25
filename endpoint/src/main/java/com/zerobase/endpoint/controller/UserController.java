package com.zerobase.endpoint.controller;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerobase.transfer.request.UserRequest;
import com.zerobase.transfer.response.ItemResponse;

import com.zerobase.endpoint.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Message<?>> postUser(@RequestBody UserRequest entity) {
        try {
            var userRes = userService.createUser(entity);
            return ResponseEntity
                    .created(URI.create("/user/" + userRes.getId()))
                    .body(
                            Message.builder()
                                    .message("user created")
                                    .data(userRes)
                                    .build());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(
                            Message
                                    .builder()
                                    .message("username already exists")
                                    .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message<?>> getUser(@PathVariable Long id) {
        try {
            var userRes = userService.findUserById(id);
            return ResponseEntity.ok(
                    Message.builder()
                            .message("user found")
                            .data(userRes)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            Message.builder()
                                    .message("user not found")
                                    .build());
        }
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<Message<?>> getUserItems(@PathVariable Long id) {
        try {
            List<ItemResponse> list = userService.findItems(id);
            return ResponseEntity.ok(
                    Message.builder()
                            .message("user's items found")
                            .data(list)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            Message.builder()
                                    .message("user not found")
                                    .build());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message<?>> putUser(@PathVariable Long id, @RequestBody UserRequest entity) {
        try {
            var userRes = userService.editUser(entity, id);
            return ResponseEntity
                    .ok(
                            Message.builder()
                                    .message("user " + id + " modified")
                                    .data(userRes)
                                    .build());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            Message
                                    .builder()
                                    .message("username not found")
                                    .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message<?>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity
                .ok(
                        Message.builder()
                                .message("user " + id + " deleted")
                                .build());
    }
}
