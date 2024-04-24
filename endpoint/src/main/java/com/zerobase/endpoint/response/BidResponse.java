package com.zerobase.endpoint.response;

import java.sql.Timestamp;

import com.zerobase.domain.entity.Bid;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class BidResponse {
    private final Long id;
    private final Long user_id;
    private final Long item_id;
    private final Long value;
    private final Timestamp created_at;

    public static BidResponse toForm(Bid bid) {
        return new BidResponse(
            bid.getId(), 
            bid.getUser().getId(), 
            bid.getItem().getId(), 
            bid.getValue(),
            bid.getCreated_at());
    }
}
