package com.zerobase.endpoint.response;

import com.zerobase.domain.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserResponse {
    private final Long id;
    private final String username;

    public static UserResponse toForm(User user) {
        return new UserResponse(
            user.getId(), 
            user.getUsername()
        );
    }
}
