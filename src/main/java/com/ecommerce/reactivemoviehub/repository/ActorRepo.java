package com.ecommerce.reactivemoviehub.repository;

import com.ecommerce.reactivemoviehub.entity.Actor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ActorRepo extends ReactiveMongoRepository<Actor, String> {
}
