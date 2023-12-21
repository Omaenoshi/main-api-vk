package com.example.mainapivk.service;

import com.example.mainapivk.conf.RabbitMQConf;
import com.example.mainapivk.dto.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public void sendLinkToQueue(Response response) {
        try {
            String exchange = RabbitMQConf.EXCHANGE_NAME;
            String routingKey = RabbitMQConf.ROUTING_KEY_RESPONSE;

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonLink = objectMapper.writeValueAsString(new Response(response.telegramId(), response.result()));

            rabbitTemplate.convertAndSend(exchange, routingKey, jsonLink);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}