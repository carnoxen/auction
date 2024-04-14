package com.zerobase.endpoint.transfer;

import com.zerobase.domain.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserForm {
    private final String username;

    public User toEntity(){
        return User.builder().username(username).build();
    }
}
