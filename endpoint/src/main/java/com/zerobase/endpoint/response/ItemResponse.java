package com.zerobase.endpoint.response;

import com.zerobase.domain.entity.Item;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ItemResponse {
    private final Long id;
    private final String name;
    private final Long start;
    private final Integer timeout;
    private final Long owner_id;

    public static ItemResponse toForm(Item item) {
        return new ItemResponse(
            item.getId(),
            item.getName(), 
            item.getStart(), 
            item.getTimeout(), 
            item.getOwner().getId()
        );
    }
}
