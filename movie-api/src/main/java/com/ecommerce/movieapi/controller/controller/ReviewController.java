package com.ecommerce.movieapi.controller.controller;

import com.ecommerce.moviecore.dto.request.review.ReviewRequestDto;
import com.ecommerce.moviecore.dto.request.review.ReviewUpdateDto;
import com.ecommerce.moviecore.dto.response.ReviewResponseDto;
import com.ecommerce.moviecore.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Mono<ResponseEntity<ReviewResponseDto>> createReview(
            @Valid @RequestBody ReviewRequestDto reviewRequestDto
    ) {
        return reviewService.createReview(reviewRequestDto)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(result));
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<ReviewResponseDto>> updateReview(
            @Valid @RequestBody ReviewUpdateDto reviewUpdateDto,
            @PathVariable("id") String id
    ) {
        return reviewService.updateReview(reviewUpdateDto, id)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.OK)
                                .body(result));

    }

    @GetMapping("/user/{id}")
    public Flux<ReviewResponseDto> getAllReviewsByUserId(
            @PathVariable("id") String userId
    ) {
        return reviewService.getAllReviewsByUserId(userId);

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteReview(
            @PathVariable("id") String id) {
        return reviewService.deleteReview(id)
                .then(Mono.just(ResponseEntity.noContent().build()));

    }
}
