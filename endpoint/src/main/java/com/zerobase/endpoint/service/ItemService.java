package com.zerobase.endpoint.service;

import org.springframework.stereotype.Service;

import com.zerobase.domain.entity.Item;
import com.zerobase.domain.entity.User;
import com.zerobase.domain.repository.ItemRepository;
import com.zerobase.domain.repository.UserRepository;
import com.zerobase.endpoint.request.ItemRequest;
import com.zerobase.endpoint.response.ItemResponse;

@Service
public class ItemService {
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    public ItemService(
        UserRepository userRepository, 
        ItemRepository itemRepository
    ) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public ItemResponse createItem(ItemRequest itemForm) {
        var owner = userRepository
            .findById(itemForm.getOwner_id())
            .orElseThrow();
        var item = itemForm.toEntity(owner);
        return ItemResponse.toForm(itemRepository.save(item));
    }

    public ItemResponse findItemById(Long id) {
        var item = itemRepository.findById(id).orElseThrow();
        return ItemResponse.toForm(item);
    }

    public ItemResponse editItem(ItemRequest itemForm, Long id) {
        User owner = userRepository
            .findById(itemForm.getOwner_id())
            .orElseThrow();
        Item item = itemForm.toEntity(owner);
        item.setId(id);
        return ItemResponse.toForm(itemRepository.save(item));
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
