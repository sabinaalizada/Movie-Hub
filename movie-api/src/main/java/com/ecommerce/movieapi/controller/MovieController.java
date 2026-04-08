package com.ecommerce.movieapi.controller;


import com.ecommerce.moviecore.dto.request.movie.MovieRequestDto;
import com.ecommerce.moviecore.dto.request.movie.MovieUpdateDto;
import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.dto.response.MovieResponseDto;
import com.ecommerce.moviecore.repository.projection.ReviewProjection;
import com.ecommerce.moviecore.service.MovieService;
import com.ecommerce.movieelastic.entity.MovieDocument;
import com.ecommerce.movieelastic.service.MovieElasticService;
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
    private final MovieElasticService movieElasticService;

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
            @PathVariable("id") String id) {
        return movieService.updateMovie(movieUpdateDto, id)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.OK)
                                .body(result));

    }

    @GetMapping("/{id}")
    public Mono<MovieResponseDto> getMovieById(
            @PathVariable("id") String id) {
        return movieService.getMovie(id);

    }

    @GetMapping
    public Flux<MovieResponseDto> getAllMovies() {
        return movieService.getAllMovies();

    }

    @GetMapping("/{id}/actors")
    public Flux<ActorResponseDto> getMovieActors(
            @PathVariable("id") String id) {
        return movieService.getMovieActors(id);

    }

    @GetMapping("/{id}/reviews")
    public Flux<ReviewProjection> getMovieReviews(
            @PathVariable("id") String id) {
        return movieService.getMovieReviews(id);

    }

    @GetMapping("/search/{title}")
    public Flux<MovieResponseDto> searchMoviesByTitle(
            @PathVariable("title") String title,
            @RequestParam(name = "moviePage",defaultValue = "0") int moviePage,
            @RequestParam(name = "movieSize",defaultValue = "10") int movieSize) {
        return movieElasticService.searchMoviesByTitle(title, moviePage, movieSize);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteMovie(
            @PathVariable("id") String id) {
        return movieService.deleteMovie(id)
                .then(Mono.just(ResponseEntity.noContent().build()));

    }

    @GetMapping("/count")
    public Mono<Long> count() {
        return movieElasticService.getMovieCount();
    }

    @GetMapping("/all")
    public Flux<MovieDocument> findAll() {
        return movieElasticService.findAll();
    }

//    @DeleteMapping("/delete-alle")
//    public Mono<Void> deleteAlle() {
//        return movieElasticService.deleteAll();
//    }
//
//    @DeleteMapping("/delete-all")
//    public Mono<Void> deleteAll() {
//        return movieService.deleteAll();
//    }
}
