package com.example.aichat.model;

public record ChatMessage(ChatRole role,
                          String message) {
}
