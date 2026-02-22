package com.ecommerce.reactivemoviehub.service.impl;

import com.ecommerce.reactivemoviehub.dto.request.review.ReviewRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.review.ReviewUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.ReviewResponseDto;
import com.ecommerce.reactivemoviehub.entity.Movie;
import com.ecommerce.reactivemoviehub.entity.Review;
import com.ecommerce.reactivemoviehub.entity.User;
import com.ecommerce.reactivemoviehub.mapper.ReviewMapper;
import com.ecommerce.reactivemoviehub.repository.MovieRepo;
import com.ecommerce.reactivemoviehub.repository.ReviewRepo;
import com.ecommerce.reactivemoviehub.repository.UserRepo;
import com.ecommerce.reactivemoviehub.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final UserRepo userRepo;
    private final MovieRepo movieRepo;
    private final ReviewMapper reviewMapper;

    @Override
    public Mono<ReviewResponseDto> createReview(ReviewRequestDto reviewRequestDto) {
        return Mono.zip(
                        userRepo.findById(reviewRequestDto.getUserId())
                                .switchIfEmpty(Mono.error(
                                        new RuntimeException("User not found"))),
                        movieRepo.findById(reviewRequestDto.getMovieId())
                                .switchIfEmpty(Mono.error(
                                        new RuntimeException("Movie not found")))
                )
                .flatMap(tuple -> {
                    User user = tuple.getT1();
                    Movie movie = tuple.getT2();

                    Review review = reviewMapper.toReview(reviewRequestDto);
                    return reviewRepo.save(review)
                            .map(saved -> {
                                ReviewResponseDto reviewResponseDto = reviewMapper.toResponseDto(saved);
                                reviewResponseDto.setReviewerName(user.getName());
                                reviewResponseDto.setMovieName(movie.getTitle());
                                return reviewResponseDto;
                            });

                });

    }

    @Override
    public Mono<ReviewResponseDto> updateReview(ReviewUpdateDto reviewUpdateDto, String id) {
        return reviewRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Review not found")))
                .flatMap(review -> {
                    reviewMapper.updateReview(review, reviewUpdateDto);
                    return reviewRepo.save(review);
                })
                .flatMap(savedReview->{
                    Mono<User> userMono = userRepo.findById(savedReview.getUserId());
                    Mono<Movie> movieMono = movieRepo.findById(savedReview.getMovieId());

                   return Mono.zip(userMono, movieMono)
                           .map(tuple->{
                               ReviewResponseDto reviewResponseDto = reviewMapper.toResponseDto(savedReview);
                               reviewResponseDto.setMovieName(tuple.getT2().getTitle());
                               reviewResponseDto.setMovieName(tuple.getT1().getName());
                               return reviewResponseDto;
                           });
                });
    }

    @Override
    public Flux<ReviewResponseDto> getAllReviewsByUserId(String userId) {
        return userRepo.findById(userId)
                .switchIfEmpty(Mono.error(
                new RuntimeException("User not found")))
                .flatMapMany(user -> reviewRepo.findAllByUserId(user.getId())
                        .flatMap(savedReview->{
                            Mono<User> userMono = userRepo.findById(savedReview.getUserId());
                            Mono<Movie> movieMono = movieRepo.findById(savedReview.getMovieId());

                            return Mono.zip(userMono, movieMono)
                                    .map(tuple->{
                                        ReviewResponseDto reviewResponseDto = reviewMapper.toResponseDto(savedReview);
                                        reviewResponseDto.setMovieName(tuple.getT2().getTitle());
                                        reviewResponseDto.setReviewerName(tuple.getT1().getName());
                                        return reviewResponseDto;
                                    });
                        }));
    }

    @Override
    public Mono<Void> deleteReview(String id) {
        return reviewRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")))
                .flatMap(reviewRepo::delete);
    }
}
