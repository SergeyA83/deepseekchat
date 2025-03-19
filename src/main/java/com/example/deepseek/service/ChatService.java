package com.example.deepseek.service;

import com.example.deepseek.model.Chat;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ChatService {
    Flux<Chat> findAll();

    Mono<Chat> findById(UUID id);

    Mono<Chat> save(Chat chat);

    Mono<Chat> saveNew();

    Mono<Void> deleteById(UUID id);

    Mono<Void> deleteAll();
}
