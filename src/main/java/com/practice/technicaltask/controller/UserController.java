package com.practice.technicaltask.controller;

import com.practice.technicaltask.dto.UserCreateDto;
import com.practice.technicaltask.dto.UserReadDto;
import com.practice.technicaltask.mapper.UserMapper;
import com.practice.technicaltask.service.UserService;
import com.practice.technicaltask.utils.RequestConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserReadDto create(@RequestBody @Valid UserCreateDto userDto) {
        return userMapper.toDto(userService.create(userDto));
    }

    @PatchMapping("/{id}")
    public UserReadDto update(@RequestBody UserCreateDto updatedUser, @PathVariable int id,
                              @RequestHeader(RequestConstants.USER_ID_HEADER) int userId) {
        return userMapper.toDto(userService.update(updatedUser, id, userId));
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable int id) {
        return userMapper.toDto(userService.findById(id));
    }

    @GetMapping
    public List<UserReadDto> findAll() {
        return userMapper.toDto(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @RequestHeader(RequestConstants.USER_ID_HEADER) int userId) {
        userService.delete(id, userId);
    }
}
