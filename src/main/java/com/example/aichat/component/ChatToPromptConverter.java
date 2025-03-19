package com.example.aichat.component;

import com.example.aichat.model.Chat;
import com.example.aichat.model.ChatMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.aichat.model.ChatRole.ASSISTANT;

@Component
public class ChatToPromptConverter {
    public Prompt convert(Chat chat, OpenAiChatOptions options) {
        List<Message> messages = chat.chatMessages()
                .stream()
                .map(this::mapToMessage)
                .collect(Collectors.toList());

        return new Prompt(messages, options);
    }

    public Message mapToMessage(ChatMessage msg) {
        return (msg.role() == ASSISTANT)
                ? new AssistantMessage(msg.message())
                : new UserMessage(msg.message());
    }
}
