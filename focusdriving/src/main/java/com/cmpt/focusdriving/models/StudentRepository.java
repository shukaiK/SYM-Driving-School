package com.cmpt.focusdriving.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students,Integer> {
    List<Students> findByName(String name);
}
