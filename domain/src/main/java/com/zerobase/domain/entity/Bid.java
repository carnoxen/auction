package com.zerobase.domain.entity;

import java.sql.Date;
import java.util.Calendar;

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
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Bid {
    @Id
    @Tsid
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private final User user;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private final Item item;
    @Column
    private final Long value;
    @Column
    @CreationTimestamp
    private final Date created_at = new Date(Calendar.getInstance().getTimeInMillis());

    @Builder
    private Bid(User user, Item item, Long value) {
        this.user = user;
        this.item = item;
        this.value = value;
    }
}
