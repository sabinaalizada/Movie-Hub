package com.ecommerce.reactivemoviehub.repository;

import com.ecommerce.reactivemoviehub.entity.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepo extends ReactiveMongoRepository<Movie, String> {
}
