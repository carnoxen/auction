package com.zerobase.endpoint.response;

import java.sql.Timestamp;

import com.zerobase.domain.entity.Item;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ItemResponse {
    private final Long id;
    private final String name;
    private final Long start;
    private final Integer timeout;
    private final Long owner_id;
    private final Timestamp created_at;
    private final Timestamp updated_at;

    public static ItemResponse toForm(Item item) {
        return new ItemResponse(
            item.getId(),
            item.getName(), 
            item.getStart(), 
            item.getTimeout(), 
            item.getOwner().getId(),
            item.getCreated_at(),
            item.getUpdated_at()
        );
    }
}
