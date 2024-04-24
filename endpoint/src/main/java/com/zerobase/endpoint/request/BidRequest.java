package com.zerobase.endpoint.request;

import java.sql.Timestamp;
import java.util.Calendar;

import com.zerobase.domain.entity.Bid;
import com.zerobase.domain.entity.Item;
import com.zerobase.domain.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class BidRequest {
    private final Long user_id;
    private final Long item_id;
    private final Long value;
    private final Timestamp created_at = new Timestamp(Calendar.getInstance().getTimeInMillis());

    public Bid toEntity(User user, Item item) {
        return Bid.builder()
            .user(user)
            .item(item)
            .value(value)
            .created_at(created_at)
            .build();
    }
}
