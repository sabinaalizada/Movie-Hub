package com.ecommerce.movieelastic.consumer;

import com.ecommerce.moviecore.event.MovieEvent;
import com.ecommerce.movieelastic.entity.MovieDocument;
import com.ecommerce.movieelastic.mapper.MovieElasticMapper;
import com.ecommerce.movieelastic.repository.MovieElasticRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final MovieElasticMapper movieElasticMapper;
    private final MovieElasticRepo movieElasticRepo;

    @KafkaListener(topics = "movie.events", groupId = "movie-search-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumer(MovieEvent message) {

        switch (message.getMovieEvent()){
            case CREATED, UPDATED -> {
                MovieDocument document = movieElasticMapper.toMovieDocument(message);

                movieElasticRepo.save(document)
                        .doOnSuccess(movieDocument -> log.info("Movie created/updated: {}", movieDocument))
                        .subscribe();
            }
            case DELETED -> {
                movieElasticRepo.deleteById(message.getId())
                        .doOnSuccess(movieDocument -> log.info("Movie deleted: {}", movieDocument))
                        .subscribe();
            }
            default -> log.warn("Unknown event type: {}", message.getMovieEvent());
        }
    }
}