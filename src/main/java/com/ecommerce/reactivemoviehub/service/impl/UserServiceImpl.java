package com.ecommerce.reactivemoviehub.service.impl;

import com.ecommerce.reactivemoviehub.dto.request.user.UserRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.user.UserUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.UserResponseDto;
import com.ecommerce.reactivemoviehub.mapper.UserMapper;
import com.ecommerce.reactivemoviehub.repository.mongo.UserRepo;
import com.ecommerce.reactivemoviehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
                .switchIfEmpty(Mono.error(
                        new RuntimeException("User already exists")
                ))
                .map(exist -> userMapper.toUser(userRequestDto))
                .flatMap(userRepo::save)
                .map(userMapper::toUserResponseDto);
    }

    @Transactional
    @Override
    public Mono<UserResponseDto> updateUser(UserUpdateDto userUpdateDto, String id) {
        return userRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("User already exists")))
                .flatMap(user -> {
                    if (userUpdateDto.getEmail() != null) {
                        return userRepo.existsByEmailAndIdNot(userUpdateDto.getEmail(), id)
                                .flatMap(exists -> {
                                    if (exists) {
                                        return Mono.error(
                                                new RuntimeException("Email already exists"));
                                    }

                                    userMapper.updateUser(user, userUpdateDto);
                                    return userRepo.save(user);
                                });
                    }

                    userMapper.updateUser(user, userUpdateDto);
                    return userRepo.save(user);
                })
                .map(userMapper::toUserResponseDto);

    }

    @Override
    public Mono<UserResponseDto> getUserById(String id) {
        return userRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("User not found")))
                .map(userMapper::toUserResponseDto);
    }

    @Transactional
    @Override
    public Mono<Void> deleteUserById(String id) {
        return userRepo.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("User not found")))
                .flatMap(userRepo::delete);
    }

    @Override
    public Flux<UserResponseDto> getAllUsers() {
        return userRepo.findAll()
                .map(userMapper::toUserResponseDto);
    }
}
