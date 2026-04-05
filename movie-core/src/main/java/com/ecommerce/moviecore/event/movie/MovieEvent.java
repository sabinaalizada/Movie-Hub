package com.ecommerce.moviecore.event.movie;

import com.ecommerce.moviecore.enums.EventType;
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
    private EventType movieEvent;
    private String title;
    private String description;
    private String genre;
    private int releaseYear;
    private LocalDateTime createdAt;
}
