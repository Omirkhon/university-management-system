package com.practice.technicaltask.controller;

import com.practice.technicaltask.dto.EnrollmentCreateDto;
import com.practice.technicaltask.dto.EnrollmentReadDto;
import com.practice.technicaltask.mapper.EnrollmentMapper;
import com.practice.technicaltask.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final EnrollmentMapper enrollmentMapper;

    @PostMapping
    public EnrollmentReadDto create(
            @RequestBody @Valid EnrollmentCreateDto enrollmentDto,
            @RequestParam int courseId, @RequestParam int userId
    ) {
        return enrollmentMapper.toDto(enrollmentService.create(enrollmentDto, courseId, userId));
    }

    @PatchMapping("/{id}")
    public EnrollmentReadDto update(
            @PathVariable int id,
            @RequestBody EnrollmentCreateDto updatedEnrollment
    ) {
        return enrollmentMapper.toDto(enrollmentService.update(id, updatedEnrollment));
    }

    @GetMapping
    public List<EnrollmentReadDto> findAll() {
        return enrollmentMapper.toDto(enrollmentService.findAll());
    }

    @GetMapping("/{id}")
    public EnrollmentReadDto findById(@PathVariable int id) {
        return enrollmentMapper.toDto(enrollmentService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public List<EnrollmentReadDto> findAllUserEnrollments(@PathVariable int userId) {
        return enrollmentMapper.toDto(enrollmentService.findAllUserEnrollments(userId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        enrollmentService.delete(id);
    }
}
