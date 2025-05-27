package com.backend.vetApp.Repository.User;

import com.backend.vetApp.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
}
