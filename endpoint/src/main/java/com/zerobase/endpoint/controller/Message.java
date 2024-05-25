package com.zerobase.endpoint.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Message<T> {
    private String message;
    private T data;

    @Builder
    private Message(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
