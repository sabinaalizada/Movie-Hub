package com.ecommerce.moviecore.service;

import com.ecommerce.moviecore.dto.request.user.UserRequestDto;
import com.ecommerce.moviecore.dto.request.user.UserUpdateDto;
import com.ecommerce.moviecore.dto.response.UserResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {
    Mono<UserResponseDto> createUser(UserRequestDto userRequestDto);

    Mono<UserResponseDto> updateUser(UserUpdateDto userUpdateDto,String id);

    Mono<UserResponseDto> getUserById(String id);

    Mono<Void> deleteUserById(String id);

    Flux<UserResponseDto> getAllUsers();
}