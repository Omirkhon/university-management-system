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
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public Course create(CourseCreateDto courseDto, int userId) {
        checkIfUserHasPermission(userId);
        Course course = new Course();
        course.setDescription(courseDto.getDescription());
        course.setTitle(courseDto.getTitle());

        return courseRepository.save(course);
    }

    public Course update(int id, CourseCreateDto updatedCourse, int userId) {
        checkIfUserHasPermission(userId);

        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found."));
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
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found."));
    }

    public void delete(int id, int userId) {
        checkIfUserHasPermission(userId);
        courseRepository.deleteById(id);
    }

    public void checkIfUserHasPermission(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not Found"));
        if (user.getRole() != Role.ADMIN) {
            throw new ForbiddenException("Access denied.");
        }
    }
}
