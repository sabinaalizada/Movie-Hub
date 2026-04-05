package com.ecommerce.movieelastic.mapper;

import com.ecommerce.moviecore.dto.response.MovieResponseDto;
import com.ecommerce.moviecore.event.movie.MovieEvent;
import com.ecommerce.movieelastic.entity.MovieDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieElasticMapper {
    MovieResponseDto toMovieResponseDto(MovieDocument movieDocument);
    MovieDocument toMovieDocument(MovieEvent movieEvent);
}
