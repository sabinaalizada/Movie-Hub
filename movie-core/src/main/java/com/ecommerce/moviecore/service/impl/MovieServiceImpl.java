package com.ecommerce.moviecore.service.impl;

import com.ecommerce.moviecore.dto.request.movie.MovieRequestDto;
import com.ecommerce.moviecore.dto.request.movie.MovieUpdateDto;
import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.dto.response.MovieResponseDto;
import com.ecommerce.moviecore.entity.Actor;
import com.ecommerce.moviecore.entity.Movie;
import com.ecommerce.moviecore.mapper.ActorMapper;
import com.ecommerce.moviecore.mapper.MovieMapper;
import com.ecommerce.moviecore.repository.mongo.ActorRepo;
import com.ecommerce.moviecore.repository.mongo.MovieRepo;
import com.ecommerce.moviecore.repository.mongo.ReviewRepo;
import com.ecommerce.moviecore.repository.mongo.UserRepo;
import com.ecommerce.moviecore.repository.projection.ReviewProjection;
import com.ecommerce.moviecore.service.MovieService;
import com.ecommerce.moviecore.utility.DuplicateChecker;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;
    private final MovieMapper movieMapper;
    private final ActorRepo actorRepo;
    private final ActorMapper actorMapper;
    private final ReviewRepo reviewRepo;
    private final UserRepo userRepo;

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
                            .flatMap(m ->
                                    getMovieReviews(m.getId())
                                            .collectList()
                                            .map(reviewProjections -> {
                                                MovieResponseDto responseDto = movieMapper.toMovieResponseDto(movie, actors);
                                                responseDto.setReviews(reviewProjections);
                                                return responseDto;
                                            })
                            );
                });


    }

    @Transactional
    @Override
    public Mono<MovieResponseDto> updateMovie(MovieUpdateDto movieUpdateDto, String id) {
        return movieRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")))
                .flatMap(existingMovie -> {
                    movieMapper.updateMovie(movieUpdateDto, existingMovie);

                    Mono<List<Actor>> actors;
                    if (movieUpdateDto.getActorId() != null) {
                        actors = Flux.fromIterable(movieUpdateDto.getActorId())
                                .flatMap(actorId ->
                                        actorRepo.findById(actorId)
                                                .switchIfEmpty(Mono.error(
                                                        new RuntimeException("Actor not found")
                                                )))

                                .collectList();
                    } else {

                        actors = Flux.fromIterable(existingMovie.getActorId())
                                .flatMap(actorRepo::findById)
                                .collectList();
                    }
                    return actors.flatMap(actorList -> {
                        if (existingMovie.getActorId() != null) {
                            existingMovie.setActorId(
                                    actorList
                                            .stream()
                                            .map(Actor::getId)
                                            .toList()
                            );
                        }
                        return movieRepo.save(existingMovie)
                                .onErrorMap(DuplicateKeyException.class,
                                        error ->
                                                new RuntimeException(error.getMessage()))
                                .flatMap(movie ->
                                        getMovieReviews(movie.getId())
                                                .collectList()
                                                .map(reviewProjections -> {
                                                    MovieResponseDto responseDto =
                                                            movieMapper.toMovieResponseDto(movie, actorList);
                                                    responseDto.setReviews(reviewProjections);
                                                    return responseDto;
                                                })
                                );


                    });
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
    public Flux<ReviewProjection> getMovieReviews(String movieId) {
        return movieRepo.findById(movieId)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")))
                .flatMapMany(movie ->
                        reviewRepo.findAllByMovieId(movieId)
                                .flatMap(review ->
                                        userRepo.findById(review.getUserId())
                                                .map(user -> new ReviewProjection(
                                                        review.getComment(),
                                                        review.getRating(),
                                                        user.getName()
                                                ))));
    }

    private Mono<MovieResponseDto> getMono(Movie existingMovie, List<Actor> actorList) {
        return movieRepo.save(existingMovie)
                .onErrorMap(DuplicateKeyException.class,
                        error ->
                                new RuntimeException(error.getMessage()))
                .flatMap(movie ->
                        getMovieReviews(movie.getId())
                                .collectList()
                                .map(reviewProjections -> {
                                    MovieResponseDto responseDto =
                                            movieMapper.toMovieResponseDto(movie, actorList);
                                    responseDto.setReviews(reviewProjections);
                                    return responseDto;
                                })
                );
    }
}
