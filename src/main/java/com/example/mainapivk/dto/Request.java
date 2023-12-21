package com.example.mainapivk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Request(@JsonProperty("telegram_user_id") int telegramId, @JsonProperty("vk_user_id")int vkId) {
}
