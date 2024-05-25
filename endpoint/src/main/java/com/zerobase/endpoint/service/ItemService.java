package com.zerobase.endpoint.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.zerobase.domain.repository.ItemRepository;
import com.zerobase.domain.repository.UserRepository;
import com.zerobase.transfer.request.ItemRequest;
import com.zerobase.transfer.response.ItemResponse;

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

    public ItemResponse createItem(ItemRequest itemForm) throws NotFoundException {
        var opOwner = userRepository
            .findById(itemForm.getOwnedBy());
        if (!opOwner.isPresent()) {
            throw new NotFoundException();
        }
        
        var owner = opOwner.get();
        var item = itemForm.toEntity(owner);

        return ItemResponse.toForm(itemRepository.save(item));
    }

    public ItemResponse findItemById(Long id) throws NotFoundException {
        var opItem = itemRepository.findById(id);
        if (!opItem.isPresent()) {
            throw new NotFoundException();
        }

        var item = opItem.get();
        return ItemResponse.toForm(item);
    }

    public ItemResponse editItem(ItemRequest itemForm, Long id) throws NotFoundException {
        var opOwner = userRepository
            .findById(itemForm.getOwnedBy());
        if (!opOwner.isPresent()) {
            throw new NotFoundException();
        }
        
        var owner = opOwner.get();
        var item = itemForm.toEntity(owner);
        item.setId(id);
        return ItemResponse.toForm(itemRepository.save(item));
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
