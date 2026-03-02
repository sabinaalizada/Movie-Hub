package com.ecommerce.moviecore.assembler;

import com.ecommerce.moviecore.dto.response.ReviewResponseDto;
import com.ecommerce.moviecore.entity.Movie;
import com.ecommerce.moviecore.entity.Review;
import com.ecommerce.moviecore.entity.User;
import com.ecommerce.moviecore.mapper.ReviewMapper;
import com.ecommerce.moviecore.repository.mongo.MovieRepo;
import com.ecommerce.moviecore.repository.mongo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ReviewAssembler {

    private final UserRepo userRepo;
    private final MovieRepo movieRepo;
    private final ReviewMapper reviewMapper;

    public Mono<ReviewResponseDto> toResponseDto(Review review) {
        Mono<User> userMono = userRepo.findById(review.getUserId())
                .switchIfEmpty(Mono.error(
                        new RuntimeException("User not found")));
        Mono<Movie> movieMono = movieRepo.findById(review.getMovieId())
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Movie not found")));

        return Mono.zip(userMono, movieMono)
                .map(tuple->{
                    ReviewResponseDto reviewResponseDto = reviewMapper.toResponseDto(review);
                    reviewResponseDto.setReviewerName(tuple.getT1().getName());
                    reviewResponseDto.setMovieName(tuple.getT2().getTitle());
                    return reviewResponseDto;
                });
    }
}
