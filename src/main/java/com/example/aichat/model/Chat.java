package com.example.aichat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public record Chat(@Id
                   UUID chatId,
                   List<ChatMessage> chatMessages) {
}
