package com.ecommerce.reactivemoviehub.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class User {
    @Id
    private String id;
    private String name;
    private String email;

    private List<String> reviewId = new ArrayList<>();
}
