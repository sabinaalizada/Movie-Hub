package com.ecommerce.moviecore.exception;

public class ActorAlreadyExistException extends RuntimeException {
    public ActorAlreadyExistException(String message) {
        super(message);
    }
}
