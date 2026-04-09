package com.ecommerce.movieapi.controller.controller;

import com.ecommerce.moviecore.dto.request.actor.ActorRequestDto;
import com.ecommerce.moviecore.dto.request.actor.ActorUpdateDto;
import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.repository.projection.MovieProjection;
import com.ecommerce.moviecore.service.ActorService;
import com.ecommerce.movieelastic.entity.ActorDocument;
import com.ecommerce.movieelastic.service.ActorElasticService;
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
    private final ActorElasticService actorElasticService;

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
            @PathVariable("id") String id) {
        return actorService.updateActor(actorUpdateDto, id)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.OK)
                                .body(result));

    }

    @GetMapping("/{id}")
    public Mono<ActorResponseDto> getActorById(
            @PathVariable("id") String id) {
        return actorService.getActor(id);

    }

    @GetMapping
    public Flux<ActorResponseDto> getAllActors() {
        return actorService.getAllActors();

    }

    @GetMapping("/{id}/movies")
    public Flux<MovieProjection> getActorMovies(@PathVariable("id") String id) {
        return actorService.getActorMovies(id);

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteActorById(
            @PathVariable("id") String id) {
        return actorService.deleteActor(id)
                .then(Mono.just(ResponseEntity.noContent().build()));

    }

    @GetMapping("/search")
    public Flux<ActorResponseDto> searchActorByFirstName(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "actorPage",defaultValue = "0") int actorPage,
            @RequestParam(name = "actorSize",defaultValue = "10") int actorSize) {
        return actorElasticService.findActorsByFirstName(firstName, actorPage, actorSize);
    }

    @GetMapping("/all")
    public Flux<ActorDocument> findAll() {
        return actorElasticService.findAll();
    }

//    @DeleteMapping("/delete-alle")
//    public Mono<Void> deleteAlle() {
//        return actorElasticService.deleteAll();
//    }
//
//    @DeleteMapping("/delete-all")
//    public Mono<Void> deleteAll() {
//        return actorService.deleteAll();
//    }
}
