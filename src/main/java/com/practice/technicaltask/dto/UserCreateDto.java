package com.practice.technicaltask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDto {
    @NotBlank(message = "Name cannot be empty")
    String name;
    @NotBlank(message = "Password cannot be empty")
    String password;
    String role;
}
