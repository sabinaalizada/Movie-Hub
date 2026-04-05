package com.ecommerce.movieelastic.repository;

import com.ecommerce.movieelastic.entity.ActorDocument;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface ActorElasticRepo extends ReactiveElasticsearchRepository<ActorDocument, String> {
}
