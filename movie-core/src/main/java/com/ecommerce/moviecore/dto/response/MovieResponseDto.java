package com.ecommerce.moviecore.dto.response;

import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.repository.projection.ReviewProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {
    private String id;
    private String title;
    private String description;
    private String genre;
    private List<ActorResponseDto> actors;
    private List<ReviewProjection> reviews;
}
