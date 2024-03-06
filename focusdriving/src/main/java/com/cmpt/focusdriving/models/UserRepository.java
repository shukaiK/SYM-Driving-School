package com.cmpt.focusdriving.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer>{
    List<Users> findByName(String name);
    List<Users> findByNameAndPassword(String name, String password);
}
