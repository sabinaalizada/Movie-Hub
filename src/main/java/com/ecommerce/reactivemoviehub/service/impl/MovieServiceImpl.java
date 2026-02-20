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
import com.ecommerce.reactivemoviehub.utility.DuplicateChecker;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;
    private final MovieMapper movieMapper;
    private final ActorRepo actorRepo;
    private final ActorMapper actorMapper;

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
                    DuplicateChecker.throwIfDuplicates(
                            actors.stream()
                                    .map(Actor::getId)
                                    .toList()
                            , "Duplicate actors found"
                    );

                    Movie movie = movieMapper.toMovie(movieRequestDto, actors);
                    return movieRepo.save(movie)
                            .onErrorMap(DuplicateKeyException.class,
                                    error ->
                                            new RuntimeException(error.getMessage()))
                            .map(m ->
                                    movieMapper.toMovieResponseDto(m, actors));
                });


    }
//Not ready
    @Override
    public Mono<MovieResponseDto> updateMovie(MovieRequestDto movieRequestDto, String id) {
        return movieRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")))
                .flatMap(movie -> {
                    Flux.fromIterable(movieRequestDto.getActorsId())
                            .flatMap(actorId ->
                                    actorRepo.findById(actorId)
                                            .switchIfEmpty(Mono.error(
                                                    new RuntimeException("Actor not found")
                                            ));
                });
    }

    @Override
    public Mono<Void> deleteMovie(String id) {
        return movieRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")))
                .flatMap(movieRepo::delete);
    }

    @Override
    public Mono<MovieResponseDto> getMovie(String id) {
        return movieRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")))
                .flatMap(movie ->
                        actorRepo.findAllByIdIn(movie.getActorId())
                                .collectList()
                                .map(actors ->
                                        movieMapper.toMovieResponseDto(movie, actors))
                );
    }

    @Override
    public Flux<MovieResponseDto> getAllMovies() {
        return movieRepo.findAll()
                .flatMap(movie ->
                        actorRepo.findAllByIdIn(movie.getActorId())
                                .collectList()
                                .map(actors ->
                                        movieMapper.toMovieResponseDto(movie, actors))
                );
    }

    @Override
    public Flux<ActorResponseDto> getMovieActors(String movieId) {
        return movieRepo.findById(movieId)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")))
                .flatMapMany(movie ->
                        actorRepo.findAllByIdIn(movie.getActorId())
                                .map(actorMapper::toResponseDto)
                );
    }

    @Override
    public Flux<ActorResponseDto> getMovieReviews(String movieId) {
        return null;
    }
}
