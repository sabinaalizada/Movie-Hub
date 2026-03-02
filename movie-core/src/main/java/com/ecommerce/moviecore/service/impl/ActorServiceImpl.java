package com.ecommerce.moviecore.service.impl;

import com.ecommerce.moviecore.dto.request.actor.ActorRequestDto;
import com.ecommerce.moviecore.dto.request.actor.ActorUpdateDto;
import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.mapper.ActorMapper;
import com.ecommerce.moviecore.repository.mongo.ActorRepo;
import com.ecommerce.moviecore.repository.mongo.MovieRepo;
import com.ecommerce.moviecore.repository.projection.MovieProjection;
import com.ecommerce.moviecore.service.ActorService;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepo actorRepo;
    private final ActorMapper actorMapper;
    private final MovieRepo movieRepo;

    @Override
    public Mono<ActorResponseDto> createActor(ActorRequestDto actorRequestDto) {
        return Mono.just(actorRequestDto)
                .map(actorMapper::toActor)
                .flatMap(actorRepo::save)
                .onErrorMap(DuplicateKeyException.class,
                        error ->
                                new RuntimeException(error.getMessage()))
                .map(actorMapper::toResponseDto);
    }

    @Override
    public Mono<ActorResponseDto> updateActor(ActorUpdateDto actorUpdateDto, String actorId) {
        return actorRepo.findById(actorId)
                .switchIfEmpty(Mono.error(new RuntimeException("Actor doesn't exist")))
                .flatMap(actor -> {
                    actorMapper.updateActor(actor, actorUpdateDto);
                    return actorRepo.save(actor);
                })
                .onErrorMap(DuplicateKeyException.class,
                        error ->
                                new RuntimeException(error.getMessage()))
                .map(actorMapper::toResponseDto);
    }

    @Override
    public Mono<Void> deleteActor(String actorId) {
        return actorRepo.findById(actorId)
                .switchIfEmpty(Mono.error
                        (new RuntimeException("Actor doesn't exist")))
                .flatMap(actorRepo::delete);
    }

    @Override
    public Mono<ActorResponseDto> getActor(String actorId) {
        return actorRepo.findById(actorId)
                .switchIfEmpty(Mono.error
                        (new RuntimeException("Actor doesn't exist")))
                .map(actorMapper::toResponseDto);
    }

    @Override
    public Flux<ActorResponseDto> getAllActors() {
        return actorRepo.findAll()
                .map(actorMapper::toResponseDto);
    }

    //projection
    @Override
    public Flux<MovieProjection> getActorMovies(String actorId) {
        return actorRepo.findById(actorId)
                .switchIfEmpty(Mono.error
                        (new RuntimeException("Actor doesn't exist")))
                .flatMapMany(actor -> movieRepo.findByActorIdContaining(actorId));
    }
}
