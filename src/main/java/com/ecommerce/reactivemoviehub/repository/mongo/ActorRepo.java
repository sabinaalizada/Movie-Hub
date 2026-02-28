package com.ecommerce.reactivemoviehub.repository.mongo;

import com.ecommerce.reactivemoviehub.entity.mongo.Actor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Collection;

public interface ActorRepo extends ReactiveMongoRepository<Actor, String> {
    Flux<Actor> findAllByIdIn(Collection<String> ids);
}
