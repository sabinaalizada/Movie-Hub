package com.ecommerce.movieapi.controller.exception.handler;

import com.ecommerce.movieapi.controller.exception.model.CustomErrorResponse;
import com.ecommerce.moviecore.exception.ActorAlreadyExistException;
import com.ecommerce.moviecore.exception.ActorNotFoundException;
import com.ecommerce.moviekafka.exception.KafkaPublishException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler(ActorAlreadyExistException.class)
    public ResponseEntity<CustomErrorResponse> actorAlreadyExist(ActorAlreadyExistException exception) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .type("errors/actor-already-exist")
                .title("Actor Already Exists")
                .status(HttpStatus.CONFLICT.value())
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);

    }

    @ExceptionHandler(ActorNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> actorNotFound(ActorNotFoundException exception) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .type("errors/actor-not-found")
                .title("Actor Not Found")
                .status(HttpStatus.NOT_FOUND.value())
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);

    }

    @ExceptionHandler(KafkaPublishException.class)
    public ResponseEntity<CustomErrorResponse> kafkaPublishFailure(KafkaPublishException exception) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .type("errors/kafka-publish-failure")
                .title("Kafka Publish Failure")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> exception(Exception exception) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .type("errors/internal-server-error")
                .title("INTERNAL_SERVER_ERROR")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

}
