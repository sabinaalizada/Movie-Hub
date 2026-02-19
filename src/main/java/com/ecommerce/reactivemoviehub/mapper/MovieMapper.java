package com.ecommerce.reactivemoviehub.mapper;

import com.ecommerce.reactivemoviehub.dto.request.movie.MovieRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.movie.MovieUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.MovieResponseDto;
import com.ecommerce.reactivemoviehub.entity.Movie;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {
    MovieResponseDto toMovieResponseDto(Movie movie);

    Movie toMovie(MovieRequestDto movieRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovieRequestDto(MovieUpdateDto movieUpdateDto, Movie movie);
}
