package com.ecommerce.moviecore.exception.actor;

public class ActorAlreadyExistException extends RuntimeException {
    public ActorAlreadyExistException(String message) {
        super(message);
    }
}
