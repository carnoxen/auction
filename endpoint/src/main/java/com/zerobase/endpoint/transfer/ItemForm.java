package com.zerobase.endpoint.transfer;

import com.zerobase.domain.entity.Item;
import com.zerobase.domain.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ItemForm {
    private final String name;
    private final Long start;
    private final Integer timeout;
    private final Long owner_id;

    public Item toEntity(User owner) {
        return Item.builder()
            .name(name)
            .start(start)
            .timeout(timeout)
            .owner(owner)
            .build();
    }

    public static ItemForm toForm(Item item) {
        return new ItemForm(item.getName(), 
            item.getStart(), 
            item.getTimeout(), 
            item.getOwner().getId());
    }
}
