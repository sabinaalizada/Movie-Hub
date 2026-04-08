package com.ecommerce.movieelastic.service;

import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.movieelastic.entity.ActorDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActorElasticService {
    Flux<ActorResponseDto> findActorsByFirstName(String firstName, int actorPage, int actorSize);
    Flux<ActorDocument> findAll();

//    Mono<Void> deleteAll();
}
