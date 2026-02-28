package com.ecommerce.reactivemoviehub.repository.mongo;

import com.ecommerce.reactivemoviehub.entity.mongo.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveMongoRepository<User, String> {
    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existsByEmailAndIdNot(String email, String id);
}
