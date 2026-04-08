package com.ecommerce.moviecore.service;

import com.ecommerce.moviecore.dto.request.movie.MovieRequestDto;
import com.ecommerce.moviecore.dto.request.movie.MovieUpdateDto;
import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.dto.response.MovieResponseDto;
import com.ecommerce.moviecore.repository.projection.ReviewProjection;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {
    Mono<MovieResponseDto> createMovie(MovieRequestDto movieRequestDto);

    Mono<MovieResponseDto> updateMovie(MovieUpdateDto movieUpdateDto, String id);

    Mono<Void> deleteMovie(String id);

    Mono<MovieResponseDto> getMovie(String id);

    Flux<MovieResponseDto> getAllMovies();

    Flux<ActorResponseDto> getMovieActors(String movieId);

    Flux<ReviewProjection> getMovieReviews(String movieId);

//    Mono<Void> deleteAll();
}
