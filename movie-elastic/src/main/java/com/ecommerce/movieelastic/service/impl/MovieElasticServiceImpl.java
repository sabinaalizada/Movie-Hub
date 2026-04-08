package com.ecommerce.movieelastic.service.impl;

import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.dto.response.MovieResponseDto;
import com.ecommerce.moviecore.repository.projection.ReviewProjection;
import com.ecommerce.moviecore.service.MovieService;
import com.ecommerce.movieelastic.entity.MovieDocument;
import com.ecommerce.movieelastic.mapper.MovieElasticMapper;
import com.ecommerce.movieelastic.repository.MovieElasticRepo;
import com.ecommerce.movieelastic.service.MovieElasticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieElasticServiceImpl implements MovieElasticService {

    private final MovieElasticRepo movieElasticRepo;
    private final MovieElasticMapper movieElasticMapper;
    private final MovieService movieService;

    @Override
    public Flux<MovieResponseDto> searchMoviesByTitle(String title, int moviePage, int movieSize) {
        PageRequest pageRequest = PageRequest.of(moviePage, movieSize);

        return movieElasticRepo.findByTitle(title, pageRequest)
                .flatMap(movieDocument -> {
                    MovieResponseDto responseDto = movieElasticMapper.toMovieResponseDto(movieDocument);

                    Mono<List<ActorResponseDto>> actors = movieService
                            .getMovieActors(movieDocument.getId())
                            .collectList();

                    Mono<List<ReviewProjection>> reviews = movieService
                            .getMovieReviews(movieDocument.getId())
                            .collectList();

                    return Mono.zip(actors, reviews)
                            .map(tuple -> {
                                responseDto.setActors(tuple.getT1());
                                responseDto.setReviews(tuple.getT2());
                                return responseDto;
                            });
                });
    }

    @Override
    public Mono<Long> getMovieCount() {
        return movieElasticRepo.count()
                .doOnSuccess(count -> log.info("Total movies in Elastic: {}", count));
    }

    @Override
    public Flux<MovieDocument> findAll() {
        return movieElasticRepo.findAll();
    }

//    @Override
//    public Mono<Void> deleteAll() {
//        return movieElasticRepo.deleteAll();
//    }
}