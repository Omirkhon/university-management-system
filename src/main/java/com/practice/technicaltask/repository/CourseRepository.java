package com.practice.technicaltask.repository;

import com.practice.technicaltask.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
