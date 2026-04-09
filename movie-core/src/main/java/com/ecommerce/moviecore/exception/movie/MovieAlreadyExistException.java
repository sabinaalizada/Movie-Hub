package com.ecommerce.moviecore.exception.movie;

public class MovieAlreadyExistException extends RuntimeException {
    public MovieAlreadyExistException(String message) {
        super(message);
    }
}
