package com.ecommerce.reactivemoviehub.service.impl;

import com.ecommerce.reactivemoviehub.dto.request.review.ReviewRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.review.ReviewUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.ReviewResponseDto;
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
                .flatMap(tuple ->
                        reviewRepo.save(
                                reviewMapper.toReview(reviewRequestDto))
                )
                .map(reviewMapper::toResponseDto);
    }

    @Override
    public Mono<ReviewResponseDto> updateReview(ReviewUpdateDto reviewUpdateDto, String id) {
        return reviewRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")))
                .flatMap(review -> {
                    reviewMapper.updateReview(review,reviewUpdateDto);
                    return reviewRepo.save(review)
                            .map(reviewMapper::toResponseDto);
                });
    }

    @Override
    public Mono<Void> deleteReview(String id) {
        return reviewRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")))
                .flatMap(reviewRepo::delete);
    }

    @Override
    public Flux<ReviewResponseDto> getAllReviewsByUserId(String userId) {
        return null;
    }
}
