package com.ecommerce.reactivemoviehub.service.es;

import com.ecommerce.reactivemoviehub.dto.response.MovieResponseDto;
import reactor.core.publisher.Flux;

public interface MovieElasticService {
    Flux<MovieResponseDto> searchMoviesByTitle(String title, int moviePage, int movieSize, int reviewPage, int reviewSize);
}
