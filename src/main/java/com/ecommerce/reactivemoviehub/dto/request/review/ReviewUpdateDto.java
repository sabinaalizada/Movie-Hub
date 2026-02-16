package com.ecommerce.reactivemoviehub.dto.request.review;

import jakarta.validation.constraints.*;

public class ReviewUpdateDto {
    @Size(max =200, message = "Title is too long")
    private String comment;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;
}
