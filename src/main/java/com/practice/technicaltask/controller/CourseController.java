package com.practice.technicaltask.controller;

import com.practice.technicaltask.dto.CourseCreateDto;
import com.practice.technicaltask.dto.CourseReadDto;
import com.practice.technicaltask.mapper.CourseMapper;
import com.practice.technicaltask.service.CourseService;
import com.practice.technicaltask.utils.RequestConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseMapper courseMapper;
    private final CourseService courseService;

    @PostMapping
    public CourseReadDto create(@RequestBody @Valid CourseCreateDto courseDto, @RequestHeader(RequestConstants.USER_ID_HEADER) int userId) {
        return courseMapper.toDto(courseService.create(courseDto, userId));
    }

    @PatchMapping("/{id}")
    public CourseReadDto update(@PathVariable int id, @RequestBody CourseCreateDto updatedCourse,
                                  @RequestHeader(RequestConstants.USER_ID_HEADER) int userId) {
        return courseMapper.toDto(courseService.update(id, updatedCourse, userId));
    }

    @GetMapping
    public List<CourseReadDto> findAll() {
        return courseMapper.toDto(courseService.findAll());
    }

    @GetMapping("/{id}")
    public CourseReadDto findById(@PathVariable int id) {
        return courseMapper.toDto(courseService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @RequestHeader(RequestConstants.USER_ID_HEADER) int userId) {
        courseService.delete(id, userId);
    }
}
