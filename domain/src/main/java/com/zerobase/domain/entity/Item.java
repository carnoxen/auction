package com.zerobase.domain.entity;

import java.sql.Timestamp;
import java.util.Calendar;

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
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Item {
    @Id
    @Tsid
    private Long id;
    @Column(nullable = false)
    private final String name;
    @Column(nullable = false)
    private Long start;
    @Column(nullable = false)
    private final Integer timeout;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private final User owner;
    @CreationTimestamp
    @Column(nullable = false)
    private final Timestamp created_at = new Timestamp(Calendar.getInstance().getTimeInMillis());
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updated_at = new Timestamp(Calendar.getInstance().getTimeInMillis());
    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private User bidder = null;

    @Builder
    private Item(String name, Long start, Integer timeout, User owner) {
        this.name = name;
        this.start = start;
        this.timeout = timeout;
        this.owner = owner;
    }
}
