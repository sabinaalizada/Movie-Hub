package com.ecommerce.moviecore.service;

import com.ecommerce.moviecore.dto.request.review.ReviewRequestDto;
import com.ecommerce.moviecore.dto.request.review.ReviewUpdateDto;
import com.ecommerce.moviecore.dto.response.ReviewResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ReviewService {
    Mono<ReviewResponseDto> createReview(ReviewRequestDto reviewRequestDto);

    Mono<ReviewResponseDto> updateReview(ReviewUpdateDto reviewUpdateDto, String id);

    Mono<Void> deleteReview(String id);

    Flux<ReviewResponseDto> getAllReviewsByUserId(String userId);
}
