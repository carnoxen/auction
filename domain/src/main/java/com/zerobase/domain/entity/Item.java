package com.zerobase.domain.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Item {
    @Id
    @Tsid
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long start;
    @Column(nullable = false)
    private Integer timeout;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User owner;
    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn
    private User bidder = null;

    @Builder
    private Item(
        String name, 
        Long start, 
        Integer timeout, 
        User owner,
        Timestamp createdAt
    ) {
        this.name = name;
        this.start = start;
        this.timeout = timeout;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }
}
