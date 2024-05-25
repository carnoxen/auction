package com.zerobase.transfer.request;

import java.sql.Timestamp;
import java.util.Calendar;

import com.zerobase.domain.entity.Bid;
import com.zerobase.domain.entity.Item;
import com.zerobase.domain.entity.User;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BidRequest {
    private final Long userId;
    private final Long itemId;
    private final Long value;
    private final Timestamp createdAt = new Timestamp(Calendar.getInstance().getTimeInMillis());

    public Bid toEntity(User user, Item item) {
        return Bid.builder()
            .user(user)
            .item(item)
            .value(value)
            .createdAt(createdAt)
            .build();
    }
}
