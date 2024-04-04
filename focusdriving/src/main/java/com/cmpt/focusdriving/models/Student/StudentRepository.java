package com.cmpt.focusdriving.models.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByName(String name);
    List<Student> findBySid(int sid);
    List<Student> findByEmail(String email);
    List<Student> findByInstructor(String instructor);
}
