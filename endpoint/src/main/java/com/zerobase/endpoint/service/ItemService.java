package com.zerobase.endpoint.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zerobase.domain.entity.Item;
import com.zerobase.domain.repository.ItemRepository;
import com.zerobase.endpoint.transfer.ItemForm;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(ItemForm itemForm) {
        Item item = itemForm.toEntity();
        return itemRepository.save(item);
    }

    public Optional<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item editItem(ItemForm itemForm, Long id) {
        Item item = itemForm.toEntity();
        item.setId(id);
        return itemRepository.save(item);
    }
}
