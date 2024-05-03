package com.zerobase.messaging;

import lombok.Data;

@Data
public class Topic {
    private Integer partition;
    private Integer replica;
}
