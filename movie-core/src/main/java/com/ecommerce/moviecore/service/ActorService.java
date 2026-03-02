package com.ecommerce.moviecore.service;

import com.ecommerce.moviecore.dto.request.actor.ActorRequestDto;
import com.ecommerce.moviecore.dto.request.actor.ActorUpdateDto;
import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.repository.projection.MovieProjection;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActorService {
    Mono<ActorResponseDto> createActor(ActorRequestDto actorRequestDto);

    Mono<ActorResponseDto> updateActor(ActorUpdateDto actorUpdateDto, String actorId);

    Mono<Void> deleteActor(String actorId);

    Mono<ActorResponseDto> getActor(String actorId);

    Flux<ActorResponseDto> getAllActors();

    Flux<MovieProjection> getActorMovies(String actorId);
}
