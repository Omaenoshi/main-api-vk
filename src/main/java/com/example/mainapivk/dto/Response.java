package com.example.mainapivk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Response(@JsonProperty("telegram_id") int telegramId, @JsonProperty("result")String result) {
}
