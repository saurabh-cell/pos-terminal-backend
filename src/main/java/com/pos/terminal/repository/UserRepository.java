package com.pos.terminal.repository;

import com.pos.terminal.model.User;
import org.springframework.data.jpa.repository.JpaRepository; // This was missing
import org.springframework.stereotype.Repository;            // This helps Spring find it
import java.util.Optional;                                   // This was missing

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // This method helps us find a user by their email during login
    Optional<User> findByEmail(String email);
}