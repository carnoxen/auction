package com.zerobase.endpoint.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zerobase.domain.entity.Item;
import com.zerobase.domain.entity.User;
import com.zerobase.domain.repository.ItemRepository;
import com.zerobase.domain.repository.UserRepository;
import com.zerobase.endpoint.transfer.ItemForm;

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

    public Item createItem(ItemForm itemForm) {
        User owner = userRepository
            .findById(itemForm.getOwner_id())
            .orElseThrow();
        Item item = itemForm.toEntity(owner);
        return itemRepository.save(item);
    }

    public Optional<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item editItem(ItemForm itemForm, Long id) {
        User owner = userRepository
            .findById(itemForm.getOwner_id())
            .orElseThrow();
        Item item = itemForm.toEntity(owner);
        item.setId(id);
        return itemRepository.save(item);
    }
}
