package com.ecommerce.reactivemoviehub.service;

import com.ecommerce.reactivemoviehub.dto.request.actor.ActorRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.actor.ActorUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.ActorResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActorService {
    Mono<ActorResponseDto> createActor(ActorRequestDto actorRequestDto);

    Mono<ActorResponseDto> updateActor(ActorUpdateDto actorUpdateDto, String actorId);

    Mono<Void> deleteActor(String actorId);

    Mono<ActorResponseDto> getActor(String actorId);

    Flux<ActorResponseDto> getAllActors();

    Flux<ActorResponseDto> getActorMovies(String actorId);
}
