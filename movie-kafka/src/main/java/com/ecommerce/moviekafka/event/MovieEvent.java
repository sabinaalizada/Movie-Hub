package com.ecommerce.moviekafka.event;

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
    private String movieId;
    private MovieEvent movieEvent;
    private String title;
    private String description;
    private String genre;
    private LocalDateTime localDateTime;
}
