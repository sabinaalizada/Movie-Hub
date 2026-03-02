package com.ecommerce.moviecore.repository.mongo;

import com.ecommerce.moviecore.entity.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReviewRepo extends ReactiveMongoRepository<Review, String> {
    Flux<Review> findAllByUserId(String userId);

    Flux<Review> findAllByMovieId(String movieId);
}
