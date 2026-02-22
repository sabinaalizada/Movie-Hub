package com.ecommerce.reactivemoviehub.mapper;

import com.ecommerce.reactivemoviehub.dto.request.review.ReviewRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.review.ReviewUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.ReviewResponseDto;
import com.ecommerce.reactivemoviehub.entity.Review;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    ReviewResponseDto toResponseDto(Review review);

    Review toReview(ReviewRequestDto reviewRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReview(@MappingTarget Review review, ReviewUpdateDto reviewUpdateDto);
}
