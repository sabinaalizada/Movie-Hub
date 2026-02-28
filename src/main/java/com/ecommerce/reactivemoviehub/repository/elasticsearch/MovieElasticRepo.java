package com.ecommerce.reactivemoviehub.repository.elasticsearch;

import com.ecommerce.reactivemoviehub.entity.elasticsearch.MovieDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Flux;


public interface MovieElasticRepo extends ReactiveElasticsearchRepository<MovieDocument, String> {
    @Query("""
        {
          "match": {
            "title": {
              "query": "?0",
              "operator": "and"
            }
          }
        }
        """)
    Flux<MovieDocument> findByTitle(String title, Pageable pageable);
}
