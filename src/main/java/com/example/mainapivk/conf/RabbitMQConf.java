package com.example.mainapivk.conf;

public class RabbitMQConf {
    public static final String EXCHANGE_NAME = "telegram_vk";
    public static final String ROUTING_KEY_REQUEST = "request";
    public static final String ROUTING_KEY_RESPONSE = "response";
    public static final String QUEUE_NAME = "telegram_request";
    public static final String RESPONSE_QUEUE_NAME = "telegram_response";
}
