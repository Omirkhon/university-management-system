package com.practice.technicaltask.controller;

import com.practice.technicaltask.dto.EnrollmentCreateDto;
import com.practice.technicaltask.dto.EnrollmentReadDto;
import com.practice.technicaltask.mapper.EnrollmentMapper;
import com.practice.technicaltask.service.EnrollmentService;
import com.practice.technicaltask.utils.RequestConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentMapper enrollmentMapper;
    private final EnrollmentService enrollmentService;

    @PostMapping
    public EnrollmentReadDto create(@RequestBody @Valid EnrollmentCreateDto enrollmentCreateDto,
                                    @RequestParam int userId, @RequestParam int courseId,
                                    @RequestHeader(RequestConstants.USER_ID_HEADER) int headerUserId) {
        return enrollmentMapper.toDto(enrollmentService.create(enrollmentCreateDto, userId, courseId, headerUserId));
    }

    @PatchMapping("/{id}")
    public EnrollmentReadDto update(@RequestBody EnrollmentCreateDto updatedEnrollment,
                                    @RequestHeader(RequestConstants.USER_ID_HEADER) int headerUserId,
                                    @PathVariable int id) {
        return enrollmentMapper.toDto(enrollmentService.update(updatedEnrollment, headerUserId, id));
    }

    @GetMapping
    public List<EnrollmentReadDto> findAll(@RequestHeader(RequestConstants.USER_ID_HEADER) int headerUserId) {
        return enrollmentMapper.toDto(enrollmentService.findAll(headerUserId));
    }

    @GetMapping("/{id}")
    public EnrollmentReadDto findById(@RequestHeader(RequestConstants.USER_ID_HEADER) int headerUserId,
                                                    @PathVariable int id) {
        return enrollmentMapper.toDto(enrollmentService.findById(headerUserId, id));
    }

    @GetMapping("/user/{userId}")
    public List<EnrollmentReadDto> findAllUserEnrollments(@RequestHeader(RequestConstants.USER_ID_HEADER) int headerUserId,
                                                    @PathVariable int userId) {
        return enrollmentMapper.toDto(enrollmentService.findAllUserEnrollments(headerUserId, userId));
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader(RequestConstants.USER_ID_HEADER) int headerUserId,
                       @PathVariable int id) {
        enrollmentService.delete(headerUserId, id);
    }
}
