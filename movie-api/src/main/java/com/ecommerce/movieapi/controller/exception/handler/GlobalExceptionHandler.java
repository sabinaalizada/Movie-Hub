package com.ecommerce.movieapi.controller.exception.handler;

import com.ecommerce.movieapi.controller.exception.model.CustomErrorResponse;
import com.ecommerce.moviecore.exception.actor.ActorAlreadyExistException;
import com.ecommerce.moviecore.exception.actor.ActorNotFoundException;
import com.ecommerce.moviecore.exception.movie.MovieAlreadyExistException;
import com.ecommerce.moviecore.exception.movie.MovieNotFoundException;
import com.ecommerce.moviecore.exception.review.ReviewNotFoundException;
import com.ecommerce.moviecore.exception.user.EmailAlreadyExistException;
import com.ecommerce.moviecore.exception.user.UserAlreadyExistException;
import com.ecommerce.moviecore.exception.user.UserNotFoundException;
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

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<CustomErrorResponse> emailAlreadyExist(EmailAlreadyExistException exception) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .type("errors/email-already-exist")
                .title("Email Already Exists")
                .status(HttpStatus.CONFLICT.value())
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);

    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> reviewNotFound(ReviewNotFoundException exception) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .type("errors/review-not-found")
                .title("Review Not Found")
                .status(HttpStatus.NOT_FOUND.value())
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);

    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<CustomErrorResponse> userAlreadyExist(UserAlreadyExistException exception) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .type("errors/user-already-exist")
                .title("User Already Exists")
                .status(HttpStatus.CONFLICT.value())
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> userNotFound(UserNotFoundException exception) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .type("errors/user-not-found")
                .title("User Not Found")
                .status(HttpStatus.NOT_FOUND.value())
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);

    }

    @ExceptionHandler(MovieAlreadyExistException.class)
    public ResponseEntity<CustomErrorResponse> movieAlreadyExist(MovieAlreadyExistException exception) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .type("errors/movie-already-exist")
                .title("Movie Already Exists")
                .status(HttpStatus.CONFLICT.value())
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);

    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> movieNotFound(MovieNotFoundException exception) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .type("errors/movie-not-found")
                .title("Movie Not Found")
                .status(HttpStatus.NOT_FOUND.value())
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);

    }

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
