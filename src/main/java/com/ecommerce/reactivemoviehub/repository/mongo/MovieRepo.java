package com.ecommerce.reactivemoviehub.repository.mongo;

import com.ecommerce.reactivemoviehub.entity.mongo.Movie;
import com.ecommerce.reactivemoviehub.repository.projection.MovieProjection;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;



public interface MovieRepo extends ReactiveMongoRepository<Movie, String> {
    Flux<MovieProjection> findByActorIdContaining(String actorId);
}
