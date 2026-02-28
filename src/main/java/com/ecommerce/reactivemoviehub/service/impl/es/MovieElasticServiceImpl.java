package com.ecommerce.reactivemoviehub.service.impl.es;

import com.ecommerce.reactivemoviehub.dto.response.ActorResponseDto;
import com.ecommerce.reactivemoviehub.dto.response.MovieResponseDto;
import com.ecommerce.reactivemoviehub.mapper.MovieMapper;
import com.ecommerce.reactivemoviehub.repository.elasticsearch.MovieElasticRepo;
import com.ecommerce.reactivemoviehub.repository.projection.ReviewProjection;
import com.ecommerce.reactivemoviehub.service.MovieService;
import com.ecommerce.reactivemoviehub.service.es.MovieElasticService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieElasticServiceImpl implements MovieElasticService {

    private final MovieElasticRepo movieElasticRepo;
    private final MovieMapper movieMapper;
    private final MovieService movieService;

    @Override
    public Flux<MovieResponseDto> searchMoviesByTitle(String title, int moviePage, int movieSize, int reviewPage, int reviewSize) {
        PageRequest pageRequest = PageRequest.of(moviePage, movieSize);

        return movieElasticRepo.findByTitle(title,pageRequest)
                .flatMap(movieDocument -> {
                    MovieResponseDto responseDto = movieMapper.toMovieResponseDto(movieDocument);

                    Mono<List<ActorResponseDto>> actors = movieService
                            .getMovieActors(movieDocument.getId())
                            .collectList();

                    Mono<List<ReviewProjection>> reviews = movieService
                            .getMovieReviews(movieDocument.getId())
                            .collectList();

                    return Mono.zip(actors,reviews)
                            .map(tuple->{
                                responseDto.setActors(tuple.getT1());
                                responseDto.setReviews(tuple.getT2());
                                return responseDto;
                            });
                });
    }
}
