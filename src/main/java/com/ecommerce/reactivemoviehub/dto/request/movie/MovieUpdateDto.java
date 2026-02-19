package com.ecommerce.reactivemoviehub.dto.request.movie;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieUpdateDto {

    @Size(max =100, message = "Title is too long")
    private String title;

    @Size(max = 500, message = "Description is too long")
    private String description;

    @Size(max = 50, message = "Genre is too long")
    private String genre;

    private List<String> actorId;

}
