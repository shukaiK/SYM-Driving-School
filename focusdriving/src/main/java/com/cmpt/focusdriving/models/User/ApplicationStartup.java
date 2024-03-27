package com.cmpt.focusdriving.models.User;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

   @Autowired
    private UserRepository userRepository;

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
            return userRepository.save(newUser);
        });
    }
}

