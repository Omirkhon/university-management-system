package com.practice.technicaltask.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserReadDto {
    int id;
    String name;
    String role;
}
