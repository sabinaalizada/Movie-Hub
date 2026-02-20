package com.ecommerce.reactivemoviehub.controller;


import com.ecommerce.reactivemoviehub.dto.request.movie.MovieRequestDto;
import com.ecommerce.reactivemoviehub.dto.response.MovieResponseDto;
import com.ecommerce.reactivemoviehub.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            @Valid @RequestBody MovieRequestDto movieRequestDto,
            @PathVariable String id) {
        return movieService.updateMovie(movieRequestDto, id)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.OK)
                                .body(result));

    }
}
