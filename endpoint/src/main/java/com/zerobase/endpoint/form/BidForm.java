package com.zerobase.endpoint.form;

import com.zerobase.domain.entity.Bid;
import com.zerobase.domain.entity.Item;
import com.zerobase.domain.entity.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BidForm {
    private final Long user_id;
    private final Long item_id;
    private final Long value;

    public Bid toEntity(User user, Item item) {
        return Bid.builder()
        .user(user)
        .item(item)
        .value(value)
        .build();
    }
}
