package com.zerobase.endpoint.transfer;

import com.zerobase.domain.entity.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserForm {
    private final String username;

    public User toEntity(){
        return User.builder().username(username).build();
    }
}
