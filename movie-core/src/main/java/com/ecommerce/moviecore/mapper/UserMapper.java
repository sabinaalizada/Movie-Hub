package com.ecommerce.moviecore.mapper;


import com.ecommerce.moviecore.dto.request.user.UserRequestDto;
import com.ecommerce.moviecore.dto.request.user.UserUpdateDto;
import com.ecommerce.moviecore.dto.response.UserResponseDto;
import com.ecommerce.moviecore.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);

    User toUser(UserRequestDto userRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateDto userUpdateDto);
}
