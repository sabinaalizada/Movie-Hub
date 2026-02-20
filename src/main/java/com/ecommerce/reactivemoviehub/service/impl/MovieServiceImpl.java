package com.ecommerce.reactivemoviehub.service.impl;

import com.ecommerce.reactivemoviehub.dto.request.movie.MovieRequestDto;
import com.ecommerce.reactivemoviehub.dto.response.ActorResponseDto;
import com.ecommerce.reactivemoviehub.dto.response.MovieResponseDto;
import com.ecommerce.reactivemoviehub.entity.Actor;
import com.ecommerce.reactivemoviehub.entity.Movie;
import com.ecommerce.reactivemoviehub.mapper.ActorMapper;
import com.ecommerce.reactivemoviehub.mapper.MovieMapper;
import com.ecommerce.reactivemoviehub.repository.ActorRepo;
import com.ecommerce.reactivemoviehub.repository.MovieRepo;
import com.ecommerce.reactivemoviehub.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;
    private final MovieMapper movieMapper;
    private final ActorRepo actorRepo;

    @Override
    public Mono<MovieResponseDto> createMovie(MovieRequestDto movieRequestDto) {
        return Flux.fromIterable(movieRequestDto.getActorsId())
                .flatMap(actorId ->
                        actorRepo.findById(actorId)
                                .switchIfEmpty(Mono.error(
                                        new RuntimeException("Actor not found")
                                ))
                )
                .collectList()
                .flatMap(actors -> {

                    Set<String> actorIds = new HashSet<>();
                    boolean hasDup = actors.stream()
                            .map(Actor::getId)
                            .anyMatch(id -> !actorIds.add(id));

                    if (hasDup) {
                        return Mono.error(
                                new RuntimeException("Duplicate actors found"));
                    }

                    Movie movie = movieMapper.toMovie(movieRequestDto, actors);
                    return movieRepo.save(movie)
                            .map(m ->
                                    movieMapper.toMovieResponseDto(m, actors));
                });


    }

    @Override
    public Mono<MovieResponseDto> updateMovie(MovieRequestDto movieRequestDto, String id) {
        return null;
    }

    @Override
    public void deleteMovie(String id) {

    }

    @Override
    public Mono<MovieResponseDto> getMovie(String id) {
        return null;
    }

    @Override
    public Flux<MovieResponseDto> getAllMovies() {
        return null;
    }

    @Override
    public Flux<ActorResponseDto> getMovieActors(String movieId) {
        return null;
    }

    @Override
    public Flux<ActorResponseDto> getMovieReviews(String movieId) {
        return null;
    }
}
