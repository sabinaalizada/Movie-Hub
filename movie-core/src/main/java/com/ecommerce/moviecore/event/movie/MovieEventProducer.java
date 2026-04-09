package com.ecommerce.moviecore.event.movie;

import reactor.core.publisher.Mono;

public interface MovieEventProducer {
    Mono<Void> sendMovieEvent(MovieEvent event);
}
