package com.cmpt.focusdriving.models.User;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.cmpt.focusdriving.models.Events.Event;
import com.cmpt.focusdriving.models.Events.EventRepository;
import com.cmpt.focusdriving.models.Student.Student;
import com.cmpt.focusdriving.models.Student.StudentRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private EventRepository eventRepository;

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
            String[] userAvailability = { "\nAvailable", "\nNot Available", "\nAvailable", "\nAvailable",
                    "\nNot Available", "\nAvailable", "\nAvailable" };

            for (int i = 0; i < daysOfWeek.length; i++) {
                String day = daysOfWeek[i];
                String availabilityStatus = userAvailability[i];
                availability.add(day + ":" + '\n' + availabilityStatus);
            }
            User newUser2 = new User();
            newUser2.setName("user");
            newUser2.setPassword(passwordEncoder.encode("password"));
            newUser2.setRole("USER");
            userRepository.save(newUser2);

            Student newStudent = new Student("student", "red@gmail.com", "778-never", "1800537", "class 7",
                    "Sfu burnaby", availability);
            studentRepository.save(newStudent);

            Student newStudent2 = new Student("another", "nlue@gmail.com", "778-never", "1800876", "class 7",
                    "Sfu burnaby", availability);
            newStudent2.setInstructor("user");
            studentRepository.save(newStudent2);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Event newEvent = new Event(LocalDateTime.parse("2024-04-08 09:30:00", formatter),
                    LocalDateTime.parse("2024-04-08 13:30:00", formatter), "BAD EVENT",
                    "unknown");
            eventRepository.save(newEvent);

            return userRepository.save(newUser);
        });
    }
}
