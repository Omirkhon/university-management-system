package com.practice.technicaltask.repository;

import com.practice.technicaltask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
