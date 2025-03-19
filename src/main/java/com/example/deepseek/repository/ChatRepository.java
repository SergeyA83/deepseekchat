package com.example.deepseek.repository;

import com.example.deepseek.model.Chat;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface ChatRepository extends ReactiveMongoRepository<Chat, UUID> {
}
