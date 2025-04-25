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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public Enrollment create(EnrollmentCreateDto dto, int courseId, int userId) {
        User currentUser = getCurrentUser();
        checkIfUserHasPermission(currentUser);
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found."));

        Enrollment enrollment = new Enrollment();
        enrollment.setDescription(dto.getDescription());
        enrollment.setCreated(LocalDateTime.now());
        enrollment.setUser(user);
        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment update(int id, EnrollmentCreateDto updatedEnrollment) {
        User currentUser = getCurrentUser();
        checkIfUserHasPermission(currentUser);

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enrollment not found."));

        if (updatedEnrollment.getDescription() != null) {
            enrollment.setDescription(updatedEnrollment.getDescription());
        }

        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> findAll() {
        checkIfUserHasPermission(getCurrentUser());
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> findAllUserEnrollments(int userId) {
        User currentUser = getCurrentUser();
        if (currentUser.getId() != userId) {
            checkIfUserHasPermission(currentUser);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found."));

        return enrollmentRepository.findAllByUser(user);
    }

    public Enrollment findById(int id) {
        checkIfUserHasPermission(getCurrentUser());
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enrollment not found."));
    }

    public void delete(int id) {
        checkIfUserHasPermission(getCurrentUser());
        enrollmentRepository.deleteById(id);
    }

    private void checkIfUserHasPermission(User user) {
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.TEACHER) {
            throw new ForbiddenException("Access denied.");
        }
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
