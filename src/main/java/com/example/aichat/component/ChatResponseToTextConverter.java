package com.example.aichat.component;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ChatResponseToTextConverter {
    public String convert(ChatResponse chatResponse) {
        return chatResponse.getResults()
                .stream()
                .map(Generation::getOutput)
                .map(AssistantMessage::getText)
                .collect(Collectors.joining());
    }
}
