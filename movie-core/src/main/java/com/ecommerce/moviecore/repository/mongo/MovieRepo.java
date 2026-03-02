package com.ecommerce.moviecore.repository.mongo;

import com.ecommerce.moviecore.entity.Movie;
import com.ecommerce.moviecore.repository.projection.MovieProjection;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;



public interface MovieRepo extends ReactiveMongoRepository<Movie, String> {
    Flux<MovieProjection> findByActorIdContaining(String actorId);
}
