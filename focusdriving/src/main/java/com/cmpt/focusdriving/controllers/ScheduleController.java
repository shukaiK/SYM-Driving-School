package com.cmpt.focusdriving.controllers;

import java.util.List;

import java.util.Optional;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import com.cmpt.focusdriving.models.Student.Student;
import com.cmpt.focusdriving.models.Student.StudentRepository;
import com.cmpt.focusdriving.models.User.User;
import com.cmpt.focusdriving.models.User.UserRepository;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// import java.io.*;

@Controller
public class ScheduleController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private StudentRepository studentRepo;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        // Return null or handle the case where no user is authenticated
        return null;
    }

    // @GetMapping("/user/viewschedule/{uid}")
    // public String showSchedule(@PathVariable("uid") Integer uid, Model model) {
    // // String username = (userRepo.findByUid(uid).get(0).getName());
    // Optional<User> userOptional = userRepo.findByUid(uid);
    // if (userOptional.isPresent()) {
    // User user = userOptional.get();
    // String username = user.getName();
    // model.addAttribute("username", username);
    // }

    // return "/user/schedule";
    // }

    // @GetMapping("/user/viewschedule/{name}")
    // public String showSchedule(@PathVariable("currentUser") String name, Model
    // model) {
    // // String username = (userRepo.findByUid(uid).get(0).getName());
    // Optional<User> userOptional = userRepo.findByName(name);
    // if (userOptional.isPresent()) {
    // User user = userOptional.get();
    // String username = user.getName();
    // model.addAttribute("currentUser", username);
    // }

    // return "/user/schedule";
    // }

    @GetMapping("/user/viewschedule")
    public String showSchedule(Model model) {
        List<Student> students = studentRepo.findByInstructor(getCurrentUsername());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        System.out.println("CURRENT USER: " + currentUser);

        model.addAttribute("students", students);
        model.addAttribute("currentUser", currentUser);
        return "/user/schedule";
    }

    // @GetMapping(value = "/username")
    // public String currentUserName(Authentication authentication) {
    // return authentication.getName();
    // }

    // @PostMapping("/user/viewschedule")
    // public String submitBooking(@RequestParam("uid") String uid) {

    // Student student = (studentRepo.findBySid(Integer.parseInt(sid))).get(0); //
    // Retrieve student from repository

    // // create new booking
    // Booking booking = new Booking();
    // booking.setStudent(student);
    // booking.setDate(date);
    // booking.setStartTime(startTime);
    // booking.setEndTime(endTime);

    // // save new booking
    // bookingRepo.save(booking);
    // return "redirect:/user/bookingview";
    // }
}
