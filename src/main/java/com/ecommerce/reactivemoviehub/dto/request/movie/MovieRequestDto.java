package com.ecommerce.reactivemoviehub.dto.request.movie;

import com.ecommerce.reactivemoviehub.validation.annotation.Year;
import jakarta.validation.constraints.NotEmpty;
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
public class MovieRequestDto {

    @NotEmpty(message = "Title cannot be empty")
    @Size(max = 100, message = "Title is too long")
    private String title;

    @Year
    private Integer releaseYear;

    @Size(max = 500, message = "Description is too long")
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Genre cannot be empty")
    @Size(max = 50, message = "Genre is too long")
    private String genre;

    @NotEmpty(message = "Actor Id cannot be empty")
    private List<String> actorsId;
}
