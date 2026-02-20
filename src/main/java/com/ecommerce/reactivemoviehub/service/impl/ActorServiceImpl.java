package com.ecommerce.reactivemoviehub.service.impl;

import com.ecommerce.reactivemoviehub.dto.request.actor.ActorRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.actor.ActorUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.ActorResponseDto;
import com.ecommerce.reactivemoviehub.mapper.ActorMapper;
import com.ecommerce.reactivemoviehub.repository.ActorRepo;
import com.ecommerce.reactivemoviehub.service.ActorService;
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

    @Override
    public Flux<ActorResponseDto> getActorMovies(String actorId) {
        return null;
    }
}
