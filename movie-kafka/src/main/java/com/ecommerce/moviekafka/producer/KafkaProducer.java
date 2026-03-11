package com.ecommerce.moviekafka.producer;

import com.ecommerce.moviekafka.event.MovieEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, MovieEvent> kafkaTemplate;

    public void sendMovieEvent(MovieEvent event) {
        kafkaTemplate.send("movie.events", event.getMovieId(), event);
    }
}
