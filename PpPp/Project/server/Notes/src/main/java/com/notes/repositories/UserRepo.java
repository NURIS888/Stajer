package com.notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
