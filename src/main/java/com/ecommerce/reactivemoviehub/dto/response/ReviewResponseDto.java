package com.ecommerce.reactivemoviehub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private String comment;
    private Integer rating;
    private String reviewerName;
    private String movieName;
}
