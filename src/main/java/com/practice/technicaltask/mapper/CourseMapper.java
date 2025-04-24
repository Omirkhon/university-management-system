package com.practice.technicaltask.mapper;

import com.practice.technicaltask.dto.CourseReadDto;
import com.practice.technicaltask.model.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseReadDto toDto(Course course);
    List<CourseReadDto> toDto(List<Course> courses);
}
