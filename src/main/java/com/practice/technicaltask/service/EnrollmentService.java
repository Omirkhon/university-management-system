package com.practice.technicaltask.service;

import com.practice.technicaltask.dto.EnrollmentCreateDto;
import com.practice.technicaltask.exceptions.ForbiddenException;
import com.practice.technicaltask.exceptions.NotFoundException;
import com.practice.technicaltask.model.Course;
import com.practice.technicaltask.model.Enrollment;
import com.practice.technicaltask.model.Role;
import com.practice.technicaltask.model.User;
import com.practice.technicaltask.repository.CourseRepository;
import com.practice.technicaltask.repository.EnrollmentRepository;
import com.practice.technicaltask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public Enrollment create(EnrollmentCreateDto enrollmentCreateDto, int userId, int courseId, int headerUserId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found."));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found."));
        checkIfUserHasPermission(headerUserId);

        Enrollment enrollment = new Enrollment();
        enrollment.setDescription(enrollmentCreateDto.getDescription());
        enrollment.setCreated(LocalDateTime.now());
        enrollment.setUser(user);
        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment update(EnrollmentCreateDto updatedEnrollment, int headerUserId, int id) {
        checkIfUserHasPermission(headerUserId);

        Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Enrollment not found."));

        if (updatedEnrollment.getDescription() != null) {
            enrollment.setDescription(updatedEnrollment.getDescription());
        }
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> findAll(int headerUserId) {
        checkIfUserHasPermission(headerUserId);
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> findAllUserEnrollments(int headerUserId, int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found."));
        if (headerUserId != userId) {
            checkIfUserHasPermission(headerUserId);
        }
        return enrollmentRepository.findAllByUser(user);
    }

    public Enrollment findById(int headerUserId, int id) {
        checkIfUserHasPermission(headerUserId);
        return enrollmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Enrollment not found."));
    }

    public void delete(int headerUserId, int id) {
        checkIfUserHasPermission(headerUserId);
        enrollmentRepository.deleteById(id);
    }

    public void checkIfUserHasPermission(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found."));
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.TEACHER) {
            throw new ForbiddenException("Access denied.");
        }
    }
}
