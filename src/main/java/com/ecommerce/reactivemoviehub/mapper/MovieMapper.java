package com.ecommerce.reactivemoviehub.mapper;

import com.ecommerce.reactivemoviehub.dto.request.movie.MovieRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.movie.MovieUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.MovieResponseDto;
import com.ecommerce.reactivemoviehub.entity.elasticsearch.MovieDocument;
import com.ecommerce.reactivemoviehub.entity.mongo.Actor;
import com.ecommerce.reactivemoviehub.entity.mongo.Movie;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ActorMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {

    @Mapping(target = "actors", source = "actors")
    MovieResponseDto toMovieResponseDto(Movie movie, List<Actor> actors);

    @Mapping(target = "actorId", source = "actors", qualifiedByName = "mapActorsToIds")
    Movie toMovie(MovieRequestDto movieRequestDto, List<Actor> actors);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovie(MovieUpdateDto movieUpdateDto, @MappingTarget Movie movie);

    MovieResponseDto toMovieResponseDto(MovieDocument movieDocument);

    @Named("mapActorsToIds")
    default List<String> mapToActorsIds(List<Actor> actors) {
        return actors.stream()
                .map(Actor::getId)
                .toList();
    }
}
