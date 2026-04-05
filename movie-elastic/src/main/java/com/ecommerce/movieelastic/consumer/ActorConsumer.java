package com.ecommerce.movieelastic.consumer;

import com.ecommerce.moviecore.event.actor.ActorEvent;
import com.ecommerce.movieelastic.entity.ActorDocument;
import com.ecommerce.movieelastic.mapper.ActorElasticMapper;
import com.ecommerce.movieelastic.repository.ActorElasticRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActorConsumer {
    private final ActorElasticMapper actorElasticMapper;
    private final ActorElasticRepo actorElasticRepo;

    @KafkaListener(topics = "actor.events", groupId = "actor-search-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumer(ActorEvent message) {

        switch (message.getActorEvent()){
            case CREATED, UPDATED -> {
                ActorDocument document = actorElasticMapper.toActorDocument(message);

                actorElasticRepo.save(document)
                        .doOnSuccess(actorDocument -> log.info("Actor created/updated: {}", actorDocument.getFirstName()))
                        .doOnError(err -> log.error("Failed to save to Elastic: {}", err.getMessage()))
                        .subscribe();
            }
            case DELETED -> {
                String title= message.getFirstName();
                actorElasticRepo.deleteById(message.getId())
                        .doOnSuccess(movieDocument -> log.info("Actor deleted: {}", title))
                        .doOnError(err -> log.error("Failed to delete from Elastic: {}", err.getMessage()))
                        .subscribe();
            }
            default -> log.warn("Unknown event type: {}", message.getActorEvent());
        }
    }
}
