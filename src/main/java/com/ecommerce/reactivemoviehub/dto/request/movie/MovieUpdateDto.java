package com.ecommerce.reactivemoviehub.dto.request.movie;

import jakarta.validation.constraints.Size;

import java.util.List;

public class MovieUpdateDto {

    @Size(max =100, message = "Title is too long")
    private String title;

    @Size(max = 500, message = "Description is too long")
    private String description;

    @Size(max = 50, message = "Genre is too long")
    private String genre;

    private List<String> actorId;

}
