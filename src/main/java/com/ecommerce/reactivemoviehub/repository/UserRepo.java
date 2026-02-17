package com.ecommerce.reactivemoviehub.repository;

import com.ecommerce.reactivemoviehub.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveMongoRepository<User, String> {
    Mono<Boolean> existsByEmail(String email);
}
