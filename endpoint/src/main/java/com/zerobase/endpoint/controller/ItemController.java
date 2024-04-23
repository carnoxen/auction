package com.zerobase.endpoint.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public ItemForm postUser(@RequestBody ItemForm entity) {
        return ItemForm.toForm(itemService.createItem(entity));
    }
    
    @GetMapping("/{id}")
    public ItemForm getUser(@PathVariable Long id) {
        var ouser = itemService.findItemById(id);
        if (!ouser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ItemForm.toForm(ouser.get());
    }
    
    @PutMapping("/{id}")
    public ItemForm putUser(@PathVariable Long id, @RequestBody ItemForm entity) {
        var ouser = itemService.findItemById(id);
        if (!ouser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ItemForm.toForm(itemService.editItem(entity, id));
    }
    
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        itemService.deleteItem(id);
        return String.format("User id %d is deleted.", id);
    }
}
