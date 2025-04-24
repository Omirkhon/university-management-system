package com.practice.technicaltask.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnrollmentReadDto {
    int id;
    String description;
    LocalDateTime created;
    UserReadDto user;
    CourseReadDto course;
}
