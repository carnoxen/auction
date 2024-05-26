package com.zerobase.transfer.request;

import java.sql.Timestamp;
import java.util.Calendar;

import com.zerobase.domain.entity.User;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserRequest {
    private final String username;
    private final Timestamp createdAt = new Timestamp(Calendar.getInstance().getTimeInMillis());

    public User toEntity(){
        return User.builder()
            .username(username)
            .createdAt(createdAt)
            .build();
    }
}
