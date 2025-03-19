package com.example.deepseek.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChatRole {
    USER("user"),
    ASSISTANT("assistant");

    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }
}
