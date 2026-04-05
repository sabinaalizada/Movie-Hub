package com.ecommerce.moviecore.service.impl;

import com.ecommerce.moviecore.dto.request.actor.ActorRequestDto;
import com.ecommerce.moviecore.dto.request.actor.ActorUpdateDto;
import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.entity.Actor;
import com.ecommerce.moviecore.enums.EventType;
import com.ecommerce.moviecore.event.actor.ActorEvent;
import com.ecommerce.moviecore.event.actor.ActorEventProducer;
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
    private final ActorEventProducer actorEventProducer;

    @Override
    public Mono<ActorResponseDto> createActor(ActorRequestDto actorRequestDto) {
        return Mono.just(actorRequestDto)
                .flatMap(requestDto-> {
                    Actor actor = actorMapper.toActor(requestDto);
                    return getMono(actor,EventType.CREATED);
                });
    }

    @Override
    public Mono<ActorResponseDto> updateActor(ActorUpdateDto actorUpdateDto, String actorId) {
        return actorRepo.findById(actorId)
                .switchIfEmpty(Mono.error(new RuntimeException("Actor doesn't exist")))
                .flatMap(actor -> {
                    actorMapper.updateActor(actor, actorUpdateDto);
                    return getMono(actor,EventType.UPDATED);

                });
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

    private Mono<? extends ActorResponseDto> getMono(Actor actor, EventType eventType) {
        return actorRepo.save(actor)
                .onErrorMap(DuplicateKeyException.class,
                        error ->
                                new RuntimeException(error.getMessage()))
                .flatMap(actorEntity -> {
                    ActorEvent actorEvent = actorMapper.toActorEvent(actorEntity);
                    actorEvent.setActorEvent(eventType);

                    return Mono.fromRunnable(() -> actorEventProducer.sendActorEvent(actorEvent))
                            .thenReturn(actorMapper.toResponseDto(actorEntity));
                });
    }
}
