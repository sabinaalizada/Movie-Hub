package com.ecommerce.moviecore.event.actor;

import reactor.core.publisher.Mono;

public interface ActorEventProducer {
    Mono<Void> sendActorEvent(ActorEvent event);
}
