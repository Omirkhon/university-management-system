package com.practice.technicaltask.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
    @OneToMany(mappedBy = "user")
    final List<Enrollment> enrollments = new ArrayList<>();
}
