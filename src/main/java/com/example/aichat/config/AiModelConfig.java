package com.example.aichat.config;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiModelConfig {
    @Bean("deepSeekModel")
    OpenAiChatModel getDeepSeekModel(@Value("${spring.ai.deep-seek.base-url}") String baseUrl,
                                     @Value("${spring.ai.deep-seek.api-key}") String apiKey) {
        OpenAiApi openAiApi = OpenAiApi
                .builder()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .build();

        return OpenAiChatModel
                .builder()
                .openAiApi(openAiApi)
                .build();
    }
}
