package com.example.aichat.repository;

import com.example.aichat.model.Chat;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface ChatRepository extends ReactiveMongoRepository<Chat, UUID> {
}
