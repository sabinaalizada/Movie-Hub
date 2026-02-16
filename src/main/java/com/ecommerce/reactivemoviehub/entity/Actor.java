package com.ecommerce.reactivemoviehub.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Actor {
    @Id
    private String id;
    private String name;
    private String bio;

    private List<String> movieId = new ArrayList<>();
}
