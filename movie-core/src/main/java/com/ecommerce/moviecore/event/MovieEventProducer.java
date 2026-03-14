package com.ecommerce.moviecore.event;

public interface MovieEventProducer {
    void sendMovieEvent(MovieEvent event);
}
