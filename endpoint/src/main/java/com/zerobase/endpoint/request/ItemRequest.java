package com.zerobase.endpoint.request;

import java.sql.Timestamp;
import java.util.Calendar;

import com.zerobase.domain.entity.Item;
import com.zerobase.domain.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ItemRequest {
    private final String name;
    private final Long start;
    private final Integer timeout;
    private final Long owner_id;
    private final Timestamp created_at = new Timestamp(Calendar.getInstance().getTimeInMillis());

    public Item toEntity(User owner) {
        return Item.builder()
            .name(name)
            .start(start)
            .timeout(timeout)
            .owner(owner)
            .created_at(created_at)
            .build();
    }
}
