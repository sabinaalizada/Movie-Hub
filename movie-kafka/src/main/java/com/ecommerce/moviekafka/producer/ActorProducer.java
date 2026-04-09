package com.ecommerce.moviekafka.producer;

import com.ecommerce.moviecore.event.actor.ActorEvent;
import com.ecommerce.moviecore.event.actor.ActorEventProducer;
import com.ecommerce.moviekafka.exception.KafkaPublishException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActorProducer implements ActorEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Mono<Void> sendActorEvent(ActorEvent event) {
        return Mono.fromFuture(()->kafkaTemplate.send("actor-events", event.getId(), event))
                .doOnSuccess(success-> log.info("Actor event sent"))
                .onErrorMap(KafkaPublishException.class,
                        error-> new KafkaPublishException(error.getMessage()))
                .then();
    }
}
