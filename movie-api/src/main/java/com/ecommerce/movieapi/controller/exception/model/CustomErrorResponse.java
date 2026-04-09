package com.ecommerce.movieapi.controller.exception.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class CustomErrorResponse {
    private final String type;
    private final String title;
    private final int status;
    private final String detail;
    private final LocalDateTime timestamp;
    private final Map<String, String> errors;
}