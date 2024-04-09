package com.zerobase.endpoint.transfer;

import com.zerobase.domain.entity.Item;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemForm {
    private final String name;
    private final Long start;
    private final Integer timeout;

    public Item toEntity() {
        return Item.builder()
            .name(name)
            .start(start)
            .timeout(timeout)
            .build();
    }
}
