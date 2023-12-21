package com.example.mainapivk.component;

import com.example.mainapivk.conf.RabbitMQConf;
import com.example.mainapivk.dto.Request;
import com.example.mainapivk.dto.Response;
import com.example.mainapivk.service.RabbitMQSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {
    private final RabbitMQSender rabbitMQSender;

    @Autowired
    public RabbitMQReceiver(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @Async
    @RabbitListener(queues = RabbitMQConf.QUEUE_NAME)
    public void receiveLinkFromQueue(String jsonLink) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Request request = objectMapper.readValue(jsonLink, Request.class);

            Response response = new Response(request.telegramId(), "success!");

            rabbitMQSender.sendLinkToQueue(response);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
