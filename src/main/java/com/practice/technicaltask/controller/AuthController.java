package com.practice.technicaltask.controller;

import com.practice.technicaltask.dto.UserCreateDto;
import com.practice.technicaltask.model.User;
import com.practice.technicaltask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.practice.technicaltask.service.JwtService;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody UserCreateDto userCreateDto) {
        User user = userService.create(userCreateDto);
        return jwtService.generateToken(new org.springframework.security.core.userdetails.User(
                user.getName(), user.getPassword(), List.of(() -> "ROLE_" + user.getRole().name())
        ));
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        UserDetails userDetails = userService.loadUserByUsername(name);
        return jwtService.generateToken(userDetails);
    }
}

