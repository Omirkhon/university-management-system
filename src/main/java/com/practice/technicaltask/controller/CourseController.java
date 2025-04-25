package com.practice.technicaltask.controller;

import com.practice.technicaltask.dto.CourseCreateDto;
import com.practice.technicaltask.dto.CourseReadDto;
import com.practice.technicaltask.mapper.CourseMapper;
import com.practice.technicaltask.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public CourseReadDto create(@RequestBody @Valid CourseCreateDto courseDto) {
        return courseMapper.toDto(courseService.create(courseDto));
    }

    @PatchMapping("/{id}")
    public CourseReadDto update(
            @PathVariable int id,
            @RequestBody CourseCreateDto updatedCourse
    ) {
        return courseMapper.toDto(courseService.update(id, updatedCourse));
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
    public void delete(@PathVariable int id) {
        courseService.delete(id);
    }
}
