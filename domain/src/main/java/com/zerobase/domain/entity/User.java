package com.zerobase.domain.entity;

import java.sql.Timestamp;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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
    @Column(unique = true)
    private final String username;
    @OneToMany(mappedBy = "owner")
    private List<Item> items;
    @CreationTimestamp
    @Column(nullable = false)
    private final Timestamp createdAt;

    @Builder
    private User(
        String username,
        Timestamp createdAt
    ) {
        this.username = username;
        this.createdAt = createdAt;
    }
}
