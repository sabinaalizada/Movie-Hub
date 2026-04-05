package com.ecommerce.moviecore.event.movie;

public interface MovieEventProducer {
    void sendMovieEvent(MovieEvent event);
}
