package com.ecommerce.reactivemoviehub.service.impl;

import com.ecommerce.reactivemoviehub.dto.request.user.UserRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.user.UserUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.UserResponseDto;
import com.ecommerce.reactivemoviehub.mapper.UserMapper;
import com.ecommerce.reactivemoviehub.repository.UserRepo;
import com.ecommerce.reactivemoviehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public Mono<UserResponseDto> createUser(UserRequestDto userRequestDto) {
        return userRepo.existsByEmail(userRequestDto.getEmail())
                .filter(exists -> !exists)
                .switchIfEmpty(Mono.error(new RuntimeException("User already exists")))
                .map(exist -> userMapper.toUser(userRequestDto))
                .flatMap(userRepo::save)
                .map(userMapper::toUserResponseDto);
    }

    @Override
    public Mono<UserResponseDto> updateUser(UserUpdateDto userUpdateDto) {
        return null;
    }

    @Override
    public Mono<UserResponseDto> getUserById(Long id) {
        return null;
    }

    @Override
    public Mono<Void> deleteUserById(Long id) {
        return null;
    }

    @Override
    public Flux<UserResponseDto> getAllUsers() {
        return null;
    }
}
