package com.ecommerce.moviecore.mapper;

import com.ecommerce.moviecore.dto.request.actor.ActorRequestDto;
import com.ecommerce.moviecore.dto.request.actor.ActorUpdateDto;
import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.moviecore.entity.Actor;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActorMapper {
    ActorResponseDto toResponseDto(Actor actor);

    Actor toActor(ActorRequestDto actorRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateActor(@MappingTarget Actor actor, ActorUpdateDto actorUpdateDto);
}
