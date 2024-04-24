package com.zerobase.endpoint.response;

import java.sql.Timestamp;

import com.zerobase.domain.entity.User;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class UserResponse {
    private final Long id;
    private final String username;
    private final Timestamp created_at;

    public static UserResponse toForm(User user) {
        return new UserResponse(
            user.getId(), 
            user.getUsername(),
            user.getCreated_at()
        );
    }
}
