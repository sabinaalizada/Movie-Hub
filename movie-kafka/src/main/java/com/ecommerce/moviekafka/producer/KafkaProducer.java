package com.ecommerce.moviekafka.producer;

import com.ecommerce.moviecore.event.MovieEvent;
import com.ecommerce.moviecore.event.MovieEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer implements MovieEventProducer {
    private final KafkaTemplate<String, MovieEvent> kafkaTemplate;

    public void sendMovieEvent(MovieEvent event) {
        kafkaTemplate.send("movie.events", event.getId(), event);
        log.info("Movie event sent");
    }
}
