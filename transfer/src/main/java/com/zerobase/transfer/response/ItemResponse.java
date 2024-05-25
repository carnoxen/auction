package com.zerobase.transfer.response;

import java.sql.Timestamp;

import com.zerobase.domain.entity.Item;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemResponse {
    private final Long id;
    private final String name;
    private final Long start;
    private final Integer timeout;
    private final Long ownedBy;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public static ItemResponse toForm(Item item) {
        return new ItemResponse(
            item.getId(),
            item.getName(), 
            item.getStart(), 
            item.getTimeout(), 
            item.getOwner().getId(),
            item.getCreatedAt(),
            item.getUpdatedAt()
        );
    }
}
