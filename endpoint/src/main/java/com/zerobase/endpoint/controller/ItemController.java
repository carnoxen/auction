package com.zerobase.endpoint.controller;

import org.springframework.web.bind.annotation.RestController;

import com.zerobase.endpoint.request.ItemRequest;
import com.zerobase.endpoint.response.ItemResponse;
import com.zerobase.endpoint.service.ItemService;

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
    public ItemResponse postUser(@RequestBody ItemRequest entity) {
        var itemRes = itemService.createItem(entity);
        return itemRes;
    }
    
    @GetMapping("/{id}")
    public ItemResponse getUser(@PathVariable Long id) {
        var itemRes = itemService.findItemById(id);
        return itemRes;
    }
    
    @PutMapping("/{id}")
    public ItemResponse putUser(@PathVariable Long id, @RequestBody ItemRequest entity) {
        var itemRes = itemService.editItem(entity, id);
        return itemRes;
    }
    
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        itemService.deleteItem(id);
        return String.format("User id %d is deleted.", id);
    }
}
