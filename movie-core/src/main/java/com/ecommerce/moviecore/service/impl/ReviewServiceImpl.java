package com.ecommerce.moviecore.service.impl;

import com.ecommerce.moviecore.assembler.ReviewAssembler;
import com.ecommerce.moviecore.dto.request.review.ReviewRequestDto;
import com.ecommerce.moviecore.dto.request.review.ReviewUpdateDto;
import com.ecommerce.moviecore.dto.response.ReviewResponseDto;
import com.ecommerce.moviecore.entity.Review;
import com.ecommerce.moviecore.mapper.ReviewMapper;
import com.ecommerce.moviecore.repository.mongo.MovieRepo;
import com.ecommerce.moviecore.repository.mongo.ReviewRepo;
import com.ecommerce.moviecore.repository.mongo.UserRepo;
import com.ecommerce.moviecore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final UserRepo userRepo;
    private final MovieRepo movieRepo;
    private final ReviewMapper reviewMapper;
    private final ReviewAssembler reviewAssembler;

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

                    Review review = reviewMapper.toReview(reviewRequestDto);
                    return reviewRepo.save(review);
                })
                .flatMap(reviewAssembler::toResponseDto);

    }

    @Transactional
    @Override
    public Mono<ReviewResponseDto> updateReview(ReviewUpdateDto reviewUpdateDto, String id) {
        return reviewRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Review not found")))
                .flatMap(review -> {
                    reviewMapper.updateReview(review, reviewUpdateDto);
                    return reviewRepo.save(review);
                })
                .flatMap(reviewAssembler::toResponseDto);
    }

    @Override
    public Flux<ReviewResponseDto> getAllReviewsByUserId(String userId) {
        return userRepo.findById(userId)
                .switchIfEmpty(Mono.error(
                new RuntimeException("User not found")))
                .flatMapMany(user -> reviewRepo.findAllByUserId(user.getId())
                        .flatMap(reviewAssembler::toResponseDto));
    }

    @Override
    public Mono<Void> deleteReview(String id) {
        return reviewRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")))
                .flatMap(reviewRepo::delete);
    }
}
