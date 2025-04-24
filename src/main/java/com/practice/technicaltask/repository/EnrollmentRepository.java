package com.practice.technicaltask.repository;

import com.practice.technicaltask.model.Enrollment;
import com.practice.technicaltask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findAllByUser(User user);
}
