package com.practice.technicaltask.mapper;

import com.practice.technicaltask.dto.EnrollmentReadDto;
import com.practice.technicaltask.model.Enrollment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    EnrollmentReadDto toDto(Enrollment enrollment);
    List<EnrollmentReadDto> toDto(List<Enrollment> enrollment);
}
