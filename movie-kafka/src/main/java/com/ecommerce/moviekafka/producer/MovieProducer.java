package com.ecommerce.moviekafka.producer;

import com.ecommerce.moviecore.event.movie.MovieEvent;
import com.ecommerce.moviecore.event.movie.MovieEventProducer;
import com.ecommerce.moviekafka.exception.KafkaPublishException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieProducer implements MovieEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Mono<Void> sendMovieEvent(MovieEvent event) {
        return Mono.fromFuture(()->kafkaTemplate.send("movie-events",event.getId(),event))
                .doOnSuccess(result->log.info("Movie event sent"))
                .onErrorMap(KafkaPublishException.class,
                        error->new KafkaPublishException(error.getMessage()))
                .then();
    }
}
