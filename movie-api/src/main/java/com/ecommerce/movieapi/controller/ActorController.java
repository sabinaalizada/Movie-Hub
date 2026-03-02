package com.ecommerce.movieapi.controller;

import com.ecommerce.moviecore.dto.request.actor.ActorRequestDto;
import com.ecommerce.moviecore.dto.request.actor.ActorUpdateDto;
import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.repository.projection.MovieProjection;
import com.ecommerce.moviecore.service.ActorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @PostMapping
    public Mono<ResponseEntity<ActorResponseDto>> createActor(
            @Valid @RequestBody ActorRequestDto actorRequestDto
    ) {
        return actorService.createActor(actorRequestDto)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(result));
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<ActorResponseDto>> updateActor(
            @Valid @RequestBody ActorUpdateDto actorUpdateDto,
            @PathVariable String id) {
        return actorService.updateActor(actorUpdateDto, id)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.OK)
                                .body(result));

    }

    @GetMapping("/{id}")
    public Mono<ActorResponseDto> getActorById(
            @PathVariable String id) {
        return actorService.getActor(id);

    }

    @GetMapping
    public Flux<ActorResponseDto> getAllActors() {
        return actorService.getAllActors();

    }

    @GetMapping("/{id}/movies")
    public Flux<MovieProjection> getActorMovies(@PathVariable String id) {
        return actorService.getActorMovies(id);

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteActorById(
            @PathVariable String id) {
        return actorService.deleteActor(id)
                .then(Mono.just(ResponseEntity.noContent().build()));

    }
}
