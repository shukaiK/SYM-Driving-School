package com.cmpt.focusdriving.models.User;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.cmpt.focusdriving.models.Student.Student;
import com.cmpt.focusdriving.models.Student.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String defaultUsername = "admin";
        userRepository.findByName(defaultUsername).orElseGet(() -> {
            User newUser = new User();
            newUser.setName(defaultUsername);
            newUser.setPassword(passwordEncoder.encode("password"));
            newUser.setRole("ADMIN");

            Student newStudent = new Student("student");
            
            studentRepository.save(newStudent);

            return userRepository.save(newUser);
        });
    }
}
