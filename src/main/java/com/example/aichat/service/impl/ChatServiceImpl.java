package com.example.aichat.service.impl;

import com.example.aichat.model.Chat;
import com.example.aichat.repository.ChatRepository;
import com.example.aichat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    @Override
    public Flux<Chat> findAll() { return chatRepository.findAll(); }

    @Override
    public Mono<Chat> findById(UUID id) {
        return chatRepository.findById(id);
    }

    @Override
    public Mono<Chat> save(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Mono<Chat> saveNew() {
        return chatRepository.save(new Chat(UUID.randomUUID(), List.of()));
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return chatRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAll() {
        return chatRepository.deleteAll();
    }
}
