package com.practice.technicaltask.service;

import com.practice.technicaltask.dto.UserCreateDto;
import com.practice.technicaltask.exceptions.ForbiddenException;
import com.practice.technicaltask.exceptions.NotFoundException;
import com.practice.technicaltask.model.Role;
import com.practice.technicaltask.model.User;
import com.practice.technicaltask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User create(UserCreateDto userCreateDto) {
        User user = new User();
        user.setName(userCreateDto.getName());
        user.setPassword(userCreateDto.getPassword());
        user.setRole(getRoleFromString(userCreateDto.getRole()));

        return userRepository.save(user);
    }

    public User update(UserCreateDto updatedUser, int id, int userId) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found."));
        if (userId != id) { // only user himself can update his own data or the admins
            checkIfUserHasPermission(userId);
        }
        if (updatedUser.getName() != null) {
            user.setName(updatedUser.getName());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getRole() != null) {
            user.setRole(getRoleFromString(updatedUser.getRole()));
        }
        return userRepository.save(user);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found."));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(int id, int userId) {
        checkIfUserHasPermission(userId);
        userRepository.deleteById(id);
    }

    public Role getRoleFromString(String role) {
        if (role == null) {
            return Role.STUDENT;
        }
        if (role.equalsIgnoreCase("ADMIN")) {
            return Role.ADMIN;
        } else if (role.equalsIgnoreCase("TEACHER")) {
            return Role.TEACHER;
        } else { // choosing the student role by default
            return Role.STUDENT;
        }
    }

    public void checkIfUserHasPermission(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not Found"));
        if (user.getRole() != Role.ADMIN) {
            throw new ForbiddenException("Access denied.");
        }
    }
}
