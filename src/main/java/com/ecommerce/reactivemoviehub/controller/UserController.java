package com.ecommerce.reactivemoviehub.controller;


import com.ecommerce.reactivemoviehub.dto.request.user.UserRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.user.UserUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.UserResponseDto;
import com.ecommerce.reactivemoviehub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Mono<ResponseEntity<UserResponseDto>> createUser(
            @Valid @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.createUser(userRequestDto)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(result));
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<UserResponseDto>> updateUser(
            @Valid @RequestBody UserUpdateDto userUpdateDto,
            @PathVariable String id) {
        return userService.updateUser(userUpdateDto, id)
                .map(result ->
                        ResponseEntity
                                .status(HttpStatus.OK)
                                .body(result));

    }

    @GetMapping("/{id}")
    public Mono<UserResponseDto> getUserById(
            @PathVariable String id) {
        return userService.getUserById(id);

    }

    @GetMapping
    public Flux<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUserById(
            @PathVariable String id) {
        return userService.deleteUserById(id)
                .then(Mono.just(ResponseEntity.noContent().build()));

    }
}
