package com.example.deepseek.model;

public record ChatMessage(ChatRole role,
                          String message) {
}
