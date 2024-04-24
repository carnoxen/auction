package com.zerobase.endpoint.response;

import com.zerobase.domain.entity.Bid;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class BidResponse {
    private final Long id;
    private final Long user_id;
    private final Long item_id;
    private final Long value;

    public static BidResponse toForm(Bid bid) {
        return new BidResponse(
            bid.getId(), 
            bid.getUser().getId(), 
            bid.getItem().getId(), 
            bid.getValue());
    }
}
