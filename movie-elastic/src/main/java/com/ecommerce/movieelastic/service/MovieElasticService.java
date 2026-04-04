package com.ecommerce.movieelastic.service;

import com.ecommerce.moviecore.dto.response.MovieResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieElasticService {
    Flux<MovieResponseDto> searchMoviesByTitle(String title, int moviePage, int movieSize);
    Mono<Long> getMovieCount();
}
