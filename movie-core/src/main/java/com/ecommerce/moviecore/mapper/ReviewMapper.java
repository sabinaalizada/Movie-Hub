package com.ecommerce.moviecore.mapper;

import com.ecommerce.moviecore.dto.request.review.ReviewRequestDto;
import com.ecommerce.moviecore.dto.request.review.ReviewUpdateDto;
import com.ecommerce.moviecore.dto.response.ReviewResponseDto;
import com.ecommerce.moviecore.entity.Review;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    ReviewResponseDto toResponseDto(Review review);

    Review toReview(ReviewRequestDto reviewRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReview(@MappingTarget Review review, ReviewUpdateDto reviewUpdateDto);
}
