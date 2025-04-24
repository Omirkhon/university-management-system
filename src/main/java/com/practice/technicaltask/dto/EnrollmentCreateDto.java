package com.practice.technicaltask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnrollmentCreateDto {
    @NotBlank(message = "Description cannot be empty")
    String description;
}
