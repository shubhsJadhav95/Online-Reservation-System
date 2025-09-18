package com.shubhs.Spring.Boo.Rest.repo;

import com.shubhs.Spring.Boo.Rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}


