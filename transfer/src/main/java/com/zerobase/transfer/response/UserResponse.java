package com.zerobase.transfer.response;

import java.sql.Timestamp;

import com.zerobase.domain.entity.User;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    private final Long id;
    private final String username;
    private final Timestamp createdAt;

    public static UserResponse toForm(User user) {
        return new UserResponse(
            user.getId(), 
            user.getUsername(),
            user.getCreatedAt()
        );
    }
}
