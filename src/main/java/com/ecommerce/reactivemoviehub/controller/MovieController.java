package com.ecommerce.reactivemoviehub.controller;


import com.ecommerce.reactivemoviehub.dto.request.movie.MovieRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.movie.MovieUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.ActorResponseDto;
import com.ecommerce.reactivemoviehub.dto.response.MovieResponseDto;
import com.ecommerce.reactivemoviehub.repository.projection.ReviewProjection;
import com.ecommerce.reactivemoviehub.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public Mono<ResponseEntity<MovieResponseDto>> createMovie(
            @Valid @RequestBody MovieRequestDto movieRequestDto
    ) {
        return movieService.createMovie(movieRequestDto)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(result));
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<MovieResponseDto>> updateMovie(
            @Valid @RequestBody MovieUpdateDto movieUpdateDto,
            @PathVariable String id) {
        return movieService.updateMovie(movieUpdateDto, id)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.OK)
                                .body(result));

    }

    @GetMapping("/{id}")
    public Mono<MovieResponseDto> getMovieById(
            @PathVariable String id) {
        return movieService.getMovie(id);

    }

    @GetMapping
    public Flux<MovieResponseDto> getAllMovies() {
        return movieService.getAllMovies();

    }

    @GetMapping("/{id}/actors")
    public Flux<ActorResponseDto> getMovieActors(
            @PathVariable String id) {
        return movieService.getMovieActors(id);

    }

    @GetMapping("/{id}/reviews")
    public Flux<ReviewProjection> getMovieReviews(
            @PathVariable String id) {
        return movieService.getMovieReviews(id);

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteMovie(
            @PathVariable String id) {
        return movieService.deleteMovie(id)
                .then(Mono.just(ResponseEntity.noContent().build()));

    }
}
