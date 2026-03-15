package com.ecommerce.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.elasticsearch.uris=http://localhost:9200",
        "spring.data.elasticsearch.repositories.enabled=true"
})
class ApplicationTests {

    @Test
    void contextLoads() {
    }

}
