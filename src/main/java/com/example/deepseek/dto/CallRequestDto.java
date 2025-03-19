package com.example.deepseek.dto;

import java.util.UUID;

public record CallRequestDto(UUID chatId,
                             String message,
                             int maxCompetitionTokens,
                             double temperature) {
}
