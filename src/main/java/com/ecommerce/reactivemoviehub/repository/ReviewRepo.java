package com.ecommerce.reactivemoviehub.repository;

import com.ecommerce.reactivemoviehub.entity.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReviewRepo extends ReactiveMongoRepository<Review, String> {

}
