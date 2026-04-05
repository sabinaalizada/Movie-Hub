package com.ecommerce.moviekafka.producer;

import com.ecommerce.moviecore.event.actor.ActorEvent;
import com.ecommerce.moviecore.event.actor.ActorEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActorProducer implements ActorEventProducer {
    private final KafkaTemplate<String, ActorEvent> kafkaTemplate;

    public void sendActorEvent(ActorEvent event) {
        kafkaTemplate.send("actor.events", event.getId(), event);
        log.info("Actor event sent");
    }
}
