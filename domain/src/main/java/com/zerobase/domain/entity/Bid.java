package com.zerobase.domain.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bid {
    @Id
    @Tsid
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Item item;
    @Column(nullable = false)
    private Long value;
    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdAt;

    @Builder
    private Bid(
        User user, 
        Item item, 
        Long value,
        Timestamp createdAt
    ) {
        this.user = user;
        this.item = item;
        this.value = value;
        this.createdAt = createdAt;
    }
}
