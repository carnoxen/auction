package com.zerobase.domain.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class User {
    @Id
    @Tsid
    private Long id;
    @Column
    private final String username;

    @Builder
    private User(String username) {
        this.username = username;
    }
}
