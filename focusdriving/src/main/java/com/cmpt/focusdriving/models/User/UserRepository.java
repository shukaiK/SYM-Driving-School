package com.cmpt.focusdriving.models.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByName(String name);
    List<User> findByNameAndPassword(String name, String password);
    List<User> findByRole(String role);
}
