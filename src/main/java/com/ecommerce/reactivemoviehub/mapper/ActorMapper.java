package com.ecommerce.reactivemoviehub.mapper;

import com.ecommerce.reactivemoviehub.dto.request.actor.ActorRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.actor.ActorUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.ActorResponseDto;
import com.ecommerce.reactivemoviehub.entity.Actor;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActorMapper {
    ActorResponseDto toResponseDto(Actor actor);

    Actor toActor(ActorRequestDto actorRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateActor(@MappingTarget Actor actor, ActorUpdateDto actorUpdateDto);
}
