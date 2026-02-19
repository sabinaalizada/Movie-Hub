package com.ecommerce.reactivemoviehub.dto.response;

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
    private List<ActorResponseDto> actors;      // fetched via actorIds
    private List<ReviewResponseDto> reviews;
}
