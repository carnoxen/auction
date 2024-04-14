package com.zerobase.domain.entity;

import java.sql.Date;
import java.util.Calendar;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private final String name;
    @Column
    private Long start;
    @Column
    private final Integer timeout;
    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private User bidder;
    @UpdateTimestamp
    @Column
    private Date updated_at = new Date(Calendar.getInstance().getTimeInMillis());

    @Builder
    private Item(String name, Long start, Integer timeout) {
        this.name = name;
        this.start = start;
        this.timeout = timeout;
    }
}
