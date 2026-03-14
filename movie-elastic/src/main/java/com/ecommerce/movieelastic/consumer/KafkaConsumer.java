package com.ecommerce.movieelastic.consumer;

import com.ecommerce.moviecore.event.MovieEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "movie.events", groupId = "group_Id", containerFactory = "kafkaListenerContainerFactory")
    public void consumer(MovieEvent message) {
        log.info("message consumed: {}", message);
    }
}