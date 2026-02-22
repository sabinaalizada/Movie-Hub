package com.ecommerce.reactivemoviehub.assembler;

import com.ecommerce.reactivemoviehub.dto.response.ReviewResponseDto;
import com.ecommerce.reactivemoviehub.entity.Movie;
import com.ecommerce.reactivemoviehub.entity.Review;
import com.ecommerce.reactivemoviehub.entity.User;
import com.ecommerce.reactivemoviehub.mapper.ReviewMapper;
import com.ecommerce.reactivemoviehub.repository.MovieRepo;
import com.ecommerce.reactivemoviehub.repository.UserRepo;
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
