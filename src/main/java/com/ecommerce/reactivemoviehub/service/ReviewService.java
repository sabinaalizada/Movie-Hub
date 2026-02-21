package com.ecommerce.reactivemoviehub.service;

import com.ecommerce.reactivemoviehub.dto.request.movie.MovieUpdateDto;
import com.ecommerce.reactivemoviehub.dto.request.review.ReviewRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.review.ReviewUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.ReviewResponseDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ReviewService {
    Mono<ReviewResponseDto> createReview(ReviewRequestDto reviewRequestDto);
    Mono<ReviewResponseDto> updateReview(ReviewUpdateDto reviewUpdateDto, String id);
    Mono<Void> deleteReview(String id);
    Mono<List<ReviewResponseDto>> getAllReviewsByUserId(String userId);
}
