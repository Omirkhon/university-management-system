package com.practice.technicaltask.mapper;

import com.practice.technicaltask.dto.UserReadDto;
import com.practice.technicaltask.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserReadDto toDto(User user);
    List<UserReadDto> toDto(List<User> users);
}
