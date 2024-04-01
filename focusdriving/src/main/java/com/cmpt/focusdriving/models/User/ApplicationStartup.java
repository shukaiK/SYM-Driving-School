package com.cmpt.focusdriving.models.User;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.cmpt.focusdriving.models.Student.Student;
import com.cmpt.focusdriving.models.Student.StudentRepository;

import java.util.ArrayList;
import java.util.List;

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
            List<String> availability = new ArrayList<>();
        String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        
        // Assume the user's availability status for each day
        String[] userAvailability = { "\nAvailable", "\nNot Available", "\nAvailable", "\nAvailable", "\nNot Available", "\nAvailable", "\nAvailable" };
        
        for (int i = 0; i < daysOfWeek.length; i++) {
            String day = daysOfWeek[i];
            String availabilityStatus = userAvailability[i];
            availability.add(day + ":" + '\n'+ availabilityStatus);
        }
            Student newStudent = new Student("student","red@gmail.com","778-never","1800537","class 7","Sfu burnaby",availability);
            studentRepository.save(newStudent);

            return userRepository.save(newUser);
        });
    }
}
