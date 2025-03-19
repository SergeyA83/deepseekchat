package com.example.aichat.service;

import com.example.aichat.dto.CallRequestDto;
import reactor.core.publisher.Flux;

public interface DeepSeekService {
    Flux<String> call(CallRequestDto callRequestDto);
}
