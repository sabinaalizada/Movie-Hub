package com.ecommerce.reactivemoviehub.mapper;

import com.ecommerce.reactivemoviehub.dto.request.movie.MovieRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.movie.MovieUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.MovieResponseDto;
import com.ecommerce.reactivemoviehub.entity.Movie;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {
    MovieResponseDto toMovieResponseDto(Movie movie);


    Movie toMovie(MovieRequestDto movieRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovieRequestDto(MovieUpdateDto movieUpdateDto, @MappingTarget Movie movie);
}
