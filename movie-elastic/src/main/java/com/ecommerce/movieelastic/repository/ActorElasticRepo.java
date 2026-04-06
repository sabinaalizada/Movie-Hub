package com.ecommerce.movieelastic.repository;

import com.ecommerce.movieelastic.entity.ActorDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Flux;

public interface ActorElasticRepo extends ReactiveElasticsearchRepository<ActorDocument, String> {
    @Query("""
        {
          "match_phrase_prefix": {
            "firstName": {
              "query": "?0"
            }
          }
        }
        """)
    Flux<ActorDocument> findByFirstName(String firstName, Pageable pageable);
}
