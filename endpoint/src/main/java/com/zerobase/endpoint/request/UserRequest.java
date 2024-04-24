package com.zerobase.endpoint.request;

import java.sql.Timestamp;
import java.util.Calendar;

import com.zerobase.domain.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserRequest {
    private final String username;
    private final Timestamp created_at = new Timestamp(Calendar.getInstance().getTimeInMillis());

    public User toEntity(){
        return User.builder()
            .username(username)
            .created_at(created_at)
            .build();
    }
}
