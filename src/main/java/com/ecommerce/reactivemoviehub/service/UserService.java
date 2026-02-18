package com.ecommerce.reactivemoviehub.service;

import com.ecommerce.reactivemoviehub.dto.request.user.UserRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.user.UserUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.UserResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService {
    Mono<UserResponseDto> createUser(UserRequestDto userRequestDto);

    Mono<UserResponseDto> updateUser(UserUpdateDto userUpdateDto,String id);

    Mono<UserResponseDto> getUserById(String id);

    Mono<Void> deleteUserById(String id);

    Flux<UserResponseDto> getAllUsers();
}