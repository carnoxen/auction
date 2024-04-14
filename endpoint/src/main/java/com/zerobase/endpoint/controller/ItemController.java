package com.zerobase.endpoint.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zerobase.domain.entity.Item;
import com.zerobase.endpoint.service.ItemService;
import com.zerobase.endpoint.transfer.ItemForm;

import org.springframework.http.HttpStatus;
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
    public Item postUser(@RequestBody ItemForm entity) {
        return itemService.createItem(entity);
    }
    
    @GetMapping("/{id}")
    public Item getUser(@PathVariable Long id) {
        var ouser = itemService.findItemById(id);
        if (!ouser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ouser.get();
    }
    
    @PutMapping("/{id}")
    public Item putUser(@PathVariable Long id, @RequestBody ItemForm entity) {
        var ouser = itemService.findItemById(id);
        if (!ouser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return itemService.editItem(entity, id);
    }
    
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        itemService.deleteItem(id);
        return String.format("User id %d is deleted.", id);
    }
}
