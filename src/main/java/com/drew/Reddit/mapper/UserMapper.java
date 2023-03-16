package com.drew.Reddit.mapper;

import com.drew.Reddit.dto.UserDto;
import com.drew.Reddit.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "userId")
    UserDto mapUsertoUserDto(User user);
}
