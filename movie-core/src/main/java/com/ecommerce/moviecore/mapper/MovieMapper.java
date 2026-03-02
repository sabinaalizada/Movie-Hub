package com.ecommerce.moviecore.mapper;

import com.ecommerce.moviecore.dto.request.movie.MovieRequestDto;
import com.ecommerce.moviecore.dto.request.movie.MovieUpdateDto;
import com.ecommerce.moviecore.dto.response.MovieResponseDto;
import com.ecommerce.moviecore.entity.Actor;
import com.ecommerce.moviecore.entity.Movie;
import com.ecommerce.moviecore.mapper.ActorMapper;
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

    @Named("mapActorsToIds")
    default List<String> mapToActorsIds(List<Actor> actors) {
        return actors.stream()
                .map(Actor::getId)
                .toList();
    }
}
