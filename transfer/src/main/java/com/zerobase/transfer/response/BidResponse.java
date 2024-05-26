package com.zerobase.transfer.response;

import java.sql.Timestamp;

import com.zerobase.domain.entity.Bid;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class BidResponse {
    private final Long id;
    private final Long userId;
    private final Long itemId;
    private final Long value;
    private final Timestamp createdAt;

    public static BidResponse toForm(Bid bid) {
        return new BidResponse(
            bid.getId(), 
            bid.getUser().getId(), 
            bid.getItem().getId(), 
            bid.getValue(),
            bid.getCreatedAt());
    }
}
