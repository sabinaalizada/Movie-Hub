package com.ecommerce.movieelastic.service.impl;

import com.ecommerce.moviecore.dto.response.ActorResponseDto;
import com.ecommerce.movieelastic.entity.ActorDocument;
import com.ecommerce.movieelastic.mapper.ActorElasticMapper;
import com.ecommerce.movieelastic.repository.ActorElasticRepo;
import com.ecommerce.movieelastic.service.ActorElasticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActorElasticServiceImpl implements ActorElasticService {
    private final ActorElasticMapper actorElasticMapper;
    private final ActorElasticRepo actorElasticRepo;

    @Override
    public Flux<ActorResponseDto> findActorsByFirstName(String firstName, int actorPage, int actorSize) {
        PageRequest pageRequest = PageRequest.of(actorPage, actorSize);

        return actorElasticRepo.findByFirstName(firstName,pageRequest)
                .map(actorElasticMapper::toActorResponseDto);
    }

    @Override
    public Flux<ActorDocument> findAll() {
        return actorElasticRepo.findAll();
    }

//    @Override
//    public Mono<Void> deleteAll() {
//        return actorElasticRepo.deleteAll();
//
//    }
}
