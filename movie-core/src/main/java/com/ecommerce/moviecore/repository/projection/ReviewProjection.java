package com.ecommerce.moviecore.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewProjection {
    private String comment;
    private Integer rating;
    private String reviewerName;
}
