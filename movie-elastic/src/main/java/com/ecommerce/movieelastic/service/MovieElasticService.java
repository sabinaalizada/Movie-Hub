package com.ecommerce.movieelastic.service;

import com.ecommerce.moviecore.dto.response.MovieResponseDto;
import reactor.core.publisher.Flux;

public interface MovieElasticService {
    Flux<MovieResponseDto> searchMoviesByTitle(String title, int moviePage, int movieSize);
}
