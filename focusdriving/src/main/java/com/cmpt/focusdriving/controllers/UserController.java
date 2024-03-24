package com.cmpt.focusdriving.controllers;


import java.util.Optional;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.cmpt.focusdriving.models.User.User;
import com.cmpt.focusdriving.models.User.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Method to show the signup form
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "user/signup"; // name of the Thymeleaf template
    }

    // Method to process the form submission
    @PostMapping("/signup")
public String processSignup(@ModelAttribute("user") User user, Model model) {
    // Check if user already exists
    Optional<User> existingUser = userRepository.findByName(user.getName());
    if (existingUser.isPresent()) {
        // User exists, add an error message to the model
        model.addAttribute("error", "Username already taken!");
        model.addAttribute("user", new User()); // Optionally reset the form
        return "user/signup"; // Return to the signup form
    }

    // No existing user found, proceed to save new user
    User newUser = new User();
    newUser.setName(user.getName());
    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
    newUser.setRole(user.getRole());
    // Set any default roles or additional properties as needed

    userRepository.save(newUser);
    return "redirect:/login"; // Redirect to login page after successful signup
}

    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request, Model model, @RequestParam Optional<String> error) {
        // You can add any attributes to the model here if needed
        // For example, to show an error message on login failure
        error.ifPresent(e -> model.addAttribute("loginError", true));

        // Check if the user is already authenticated, then redirect them away from the login page
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/user/dashboard"; // or wherever you want to redirect authenticated users
        }

        return "user/login"; // name of the Thymeleaf template for the login page
    }

    @GetMapping("/user/dashboard")
    public String showDashboard() {
        return "user/dashboard"; // Name of the Thymeleaf template without the .html extension
    }

    @GetMapping("/admin/dashboard")
    public String showOwnerDashboard() {
        return "user/ownerdashboard"; // Name of the Thymeleaf template without the .html extension
    }
}
