package com.ecommerce.moviecore.exception.actor;

public class ActorNotFoundException extends RuntimeException {
    public ActorNotFoundException(String message) {
        super(message);
    }
}
