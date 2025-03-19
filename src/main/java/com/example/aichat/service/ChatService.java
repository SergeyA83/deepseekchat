package com.example.aichat.service;

import com.example.aichat.model.Chat;
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
