package com.ecommerce.reactivemoviehub.dto.response;

import java.util.List;

public class MovieResponseDto {
    private String id;
    private String title;
    private String description;
    private String genre;

    private List<ActorResponseDto> actors;      // fetched via actorIds
    private List<ReviewResponseDto> reviews;
}
