package com.assignment.bidding.mapper;

import com.assignment.bidding.dto.UserDto;
import com.assignment.bidding.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDto dto);

    UserDto toUserDto(User user);
}
