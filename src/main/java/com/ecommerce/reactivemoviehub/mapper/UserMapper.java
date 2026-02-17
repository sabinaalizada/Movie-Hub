package com.ecommerce.reactivemoviehub.mapper;


import com.ecommerce.reactivemoviehub.dto.request.user.UserRequestDto;
import com.ecommerce.reactivemoviehub.dto.request.user.UserUpdateDto;
import com.ecommerce.reactivemoviehub.dto.response.UserResponseDto;
import com.ecommerce.reactivemoviehub.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);

    User toUser(UserRequestDto userRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateDto userUpdateDto);
}
