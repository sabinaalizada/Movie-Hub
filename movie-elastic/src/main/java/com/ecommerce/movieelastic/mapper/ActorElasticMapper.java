package com.ecommerce.movieelastic.mapper;

import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.event.actor.ActorEvent;
import com.ecommerce.movieelastic.entity.ActorDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActorElasticMapper {
    ActorResponseDto toActorResponseDto(ActorDocument actorDocument);
    ActorDocument toActorDocument(ActorEvent actorEvent);
}
