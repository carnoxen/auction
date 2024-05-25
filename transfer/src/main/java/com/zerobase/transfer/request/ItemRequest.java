package com.zerobase.transfer.request;

import java.sql.Timestamp;
import java.util.Calendar;

import com.zerobase.domain.entity.Item;
import com.zerobase.domain.entity.User;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemRequest {
    private final String name;
    private final Long start;
    private final Integer timeout;
    private final Long ownedBy;
    private final Timestamp createdAt = new Timestamp(Calendar.getInstance().getTimeInMillis());

    public Item toEntity(User owner) {
        return Item.builder()
            .name(name)
            .start(start)
            .timeout(timeout)
            .owner(owner)
            .createdAt(createdAt)
            .build();
    }
}
