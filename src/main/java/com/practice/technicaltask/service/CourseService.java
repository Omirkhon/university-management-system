package com.practice.technicaltask.service;

import com.practice.technicaltask.dto.CourseCreateDto;
import com.practice.technicaltask.exceptions.ForbiddenException;
import com.practice.technicaltask.exceptions.NotFoundException;
import com.practice.technicaltask.model.Course;
import com.practice.technicaltask.model.Role;
import com.practice.technicaltask.model.User;
import com.practice.technicaltask.repository.CourseRepository;
import com.practice.technicaltask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public Course create(CourseCreateDto courseDto) {
        User user = getAuthenticatedUser();
        checkIfUserHasPermission(user);

        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());

        return courseRepository.save(course);
    }

    public Course update(int id, CourseCreateDto updatedCourse) {
        User user = getAuthenticatedUser();
        checkIfUserHasPermission(user);

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found."));

        if (updatedCourse.getTitle() != null) {
            course.setTitle(updatedCourse.getTitle());
        }
        if (updatedCourse.getDescription() != null) {
            course.setDescription(updatedCourse.getDescription());
        }

        return courseRepository.save(course);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found."));
    }

    public void delete(int id) {
        User user = getAuthenticatedUser();
        checkIfUserHasPermission(user);
        courseRepository.deleteById(id);
    }

    private void checkIfUserHasPermission(User user) {
        if (user == null || !(user.getRole() == Role.ADMIN || user.getRole() == Role.TEACHER)) {
            throw new ForbiddenException("Access denied.");
        }
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByName(username)
                .orElseThrow(() -> new NotFoundException("Authenticated user not found."));
    }
}
