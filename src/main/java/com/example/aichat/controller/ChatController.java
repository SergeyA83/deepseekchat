package com.example.aichat.controller;


import com.example.aichat.model.Chat;
import com.example.aichat.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Flux<Chat> findAll() {
        return chatService.findAll();
    }

    @GetMapping("/chat/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Mono<Chat> findById(@PathVariable UUID id) {
        return chatService.findById(id);
    }

    @PostMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Mono<Chat> saveNew() {
        return chatService.saveNew();
    }

    @DeleteMapping("/chat/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Mono<Void> deleteById(@PathVariable UUID id) {
        return chatService.deleteById(id);
    }

    @DeleteMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Mono<Void> deleteAll() {
        return chatService.deleteAll();
    }
}
