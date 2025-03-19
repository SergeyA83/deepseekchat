package com.example.deepseek.service.impl;

import com.example.deepseek.component.ChatResponseToTextConverter;
import com.example.deepseek.component.ChatToPromptConverter;
import com.example.deepseek.dto.CallRequestDto;
import com.example.deepseek.model.Chat;
import com.example.deepseek.model.ChatMessage;
import com.example.deepseek.service.ChatService;
import com.example.deepseek.service.DeepSeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.deepseek.model.ChatRole.ASSISTANT;
import static com.example.deepseek.model.ChatRole.USER;

@Service
@RequiredArgsConstructor
public class DeepSeekServiceImpl implements DeepSeekService {
    @Qualifier("deepSeekModel")
    private final OpenAiChatModel deepSeekModel;
    private final ChatToPromptConverter chatToPromptConverter;
    private final ChatResponseToTextConverter chatResponseToTextConverter;
    private final ChatService chatService;

    @Value("${spring.ai.deep-seek.model}") String model;

    @Override
    public Flux<String> call(CallRequestDto callRequestDto) {
        return chatService.findById(callRequestDto.chatId())
                .flatMap(chat -> saveUserChatMessage(chat, callRequestDto.message()))
                .flatMapMany(chat -> streamChatResponse(chat, callRequestDto));
    }

    private Flux<String> streamChatResponse(Chat chat, CallRequestDto callRequestDto) {
        OpenAiChatOptions options = createOpenAiChatOptions(model,
                callRequestDto.maxCompetitionTokens(),
                callRequestDto.temperature());

        StringBuilder assistantMessageBuilder = new StringBuilder();

        return callDeepSeekStream(chatToPromptConverter.convert(chat, options))
                .map(chatResponseToTextConverter::convert)
                .doOnNext(assistantMessageBuilder::append)
                .doOnComplete(() ->
                        saveAssistantChatMessage(chat, assistantMessageBuilder.toString())
                                .subscribe());
    }

    private OpenAiChatOptions createOpenAiChatOptions(String model,
                                                      int maxCompetitionTokens,
                                                      double temperature) {
        return OpenAiChatOptions.builder()
                .model(model)
                .maxCompletionTokens(maxCompetitionTokens)
                .temperature(temperature)
                .streamUsage(true)
                .build();
    }

    private Flux<ChatResponse> callDeepSeekStream(Prompt prompt) {
        return deepSeekModel
                .stream(prompt);
    }

    private Mono<Chat> saveUserChatMessage(Chat chat, String userMessage) {
        ChatMessage chatMessage = new ChatMessage(USER, userMessage);
        return saveChatMessage(chat, chatMessage);
    }

    private Mono<Chat> saveAssistantChatMessage(Chat chat, String assistantMessage) {
        ChatMessage chatMessage = new ChatMessage(ASSISTANT, assistantMessage);
        return saveChatMessage(chat, chatMessage);
    }

    private Mono<Chat> saveChatMessage(Chat chat, ChatMessage chatMessage) {
        chat.chatMessages().add(chatMessage);
        return chatService.save(chat);
    }
}
