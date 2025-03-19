package com.example.deepseek.service;

import com.example.deepseek.dto.CallRequestDto;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

public interface DeepSeekService {
    Flux<String> call(CallRequestDto callRequestDto);
}
