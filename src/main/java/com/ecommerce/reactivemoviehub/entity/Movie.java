package com.ecommerce.reactivemoviehub.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Movie {

    @Id
    private String id;
    private String title;
    private String description;
    private String genre;

    private List<Review> reviews = new ArrayList<>();
    private List<String> actorId = new ArrayList<>();
}
