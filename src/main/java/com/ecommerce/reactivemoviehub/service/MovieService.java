package com.ecommerce.reactivemoviehub.service;

import com.ecommerce.reactivemoviehub.dto.request.movie.MovieRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.movie.MovieUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.ActorResponseDto;
import com.ecommerce.reactivemoviehub.dto.response.MovieResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {
    Mono<MovieResponseDto> createMovie(MovieRequestDto movieRequestDto);

    Mono<MovieResponseDto> updateMovie(MovieUpdateDto movieUpdateDto, String id);

    Mono<Void> deleteMovie(String id);

    Mono<MovieResponseDto> getMovie(String id);

    Flux<MovieResponseDto> getAllMovies();

    Flux<ActorResponseDto> getMovieActors(String movieId);

    Flux<ActorResponseDto> getMovieReviews(String movieId);

}
