package com.ecommerce.reactivemoviehub.repository;

import com.ecommerce.reactivemoviehub.entity.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReviewRepo extends ReactiveMongoRepository<Review, String> {
    Flux<Review> findAllByUserId(String userId);
}
