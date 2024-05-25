package com.zerobase.endpoint.controller;

import java.net.URI;

import org.springframework.web.bind.annotation.RestController;

import com.zerobase.transfer.request.ItemRequest;
import com.zerobase.transfer.response.ItemResponse;

import com.zerobase.endpoint.service.ItemService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/item")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Message<?>> postUser(@RequestBody ItemRequest entity) {
        try {
            ItemResponse itemRes = itemService.createItem(entity);
            return ResponseEntity
                .created(URI.create("/item/" + itemRes.getId()))
                .body(
                    Message.builder()
                        .message("item created")
                        .data(itemRes)
                        .build()
                );
        } catch (NotFoundException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(
                    Message.builder().message("owner is not found").build()
                );
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Message<?>> getUser(@PathVariable Long id) {
        try {
            var itemRes = itemService.findItemById(id);
            return ResponseEntity.ok(
                    Message.builder()
                            .message("item found")
                            .data(itemRes)
                            .build());
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    Message.builder().message("item not found").build()
                );
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Message<?>> putUser(@PathVariable Long id, @RequestBody ItemRequest entity) {
        try {
            var itemRes = itemService.editItem(entity, id);
            return ResponseEntity.ok(
                    Message.builder()
                            .message("item editted")
                            .data(itemRes)
                            .build());
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    Message.builder().message("item not found").build()
                );
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Message<?>> deleteUser(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity
                .ok(
                        Message.builder()
                                .message("item " + id + " deleted")
                                .build());
    }
}
