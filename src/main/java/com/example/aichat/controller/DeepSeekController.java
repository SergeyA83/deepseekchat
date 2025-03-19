package com.example.aichat.controller;

import com.example.aichat.dto.CallRequestDto;
import com.example.aichat.service.DeepSeekService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class DeepSeekController {
    private final DeepSeekService deepSeekService;

    @PostMapping(path = "/deepseek/call", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Flux<String> call(@RequestBody CallRequestDto callRequestDto) {
        return deepSeekService.call(callRequestDto);
    }
}
