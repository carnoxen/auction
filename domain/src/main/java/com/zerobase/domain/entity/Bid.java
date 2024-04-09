package com.zerobase.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @GeneratedValue
    private Long id;
    @Column
    @ManyToOne
    private final User user;
    @Column
    @ManyToOne
    private final Item item;
    @Column
    private final Long value;

    @Builder
    private Bid(User user, Item item, Long value) {
        this.user = user;
        this.item = item;
        this.value = value;
    }
}
