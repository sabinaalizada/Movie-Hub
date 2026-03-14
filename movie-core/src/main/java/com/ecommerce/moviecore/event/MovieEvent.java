package com.ecommerce.moviecore.event;

import com.ecommerce.moviecore.enums.MovieEventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieEvent {
    private String id;
    private MovieEventType movieEvent;
    private String title;
    private String description;
    private String genre;
    private int releaseYear;
    private LocalDateTime createdAt;
}
