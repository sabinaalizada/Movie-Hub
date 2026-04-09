package com.ecommerce.moviekafka.exception;

public class KafkaPublishException extends RuntimeException {
    public KafkaPublishException(String message) {
        super(message);
    }
}
