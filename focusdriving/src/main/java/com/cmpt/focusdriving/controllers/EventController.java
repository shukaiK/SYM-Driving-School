package com.cmpt.focusdriving.controllers;

import com.cmpt.focusdriving.models.Events.Event;
import com.cmpt.focusdriving.models.Events.EventRepository;
import com.cmpt.focusdriving.models.Student.StudentRepository;
import com.cmpt.focusdriving.models.User.User;
import com.cmpt.focusdriving.models.User.UserRepository;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import ch.qos.logback.core.model.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepo;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    UserRepository userRepo;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }

        return null;
    }

    @GetMapping("/api/allevents")
    List<Event> allEvents() {
        Optional<User> optionalUser = userRepo.findByName(getCurrentUsername());
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println(user.getName());

            if (user.getRole().equals("ADMIN")) {
                return eventRepo.findAll();
            } else {

                return null;
            }
        } else {

            // if user not found
            return null;
        }
    }

    @GetMapping("/api/instructorevents")
    List<Event> instructorEvents() {
        Optional<User> optionalUser = userRepo.findByName(getCurrentUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getRole().equals("USER")) {

                return eventRepo.findByInstructorName(user.getName());
            } else {

                return null;
            }
        } else {

            // if user not found
            return null;
        }
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody EventCreateParams params) {

        System.out.println("----------CREATE MAPPING TRIGGERED --------");
        System.out.println("----------STUDENT ID: " + params.sid + " --------");

        if (studentRepo.findBySid(params.sid).isEmpty()) {
            // handle error: student not found
            System.out.println("----------STUDENT NOT FOUND--------");
            return null;
        }

        Event e = new Event();
        e.setStart(params.start);
        e.setEnd(params.end);
        e.setText(params.text);
        e.setSid(params.sid);
        e.setInstructorName(getCurrentUsername());

        eventRepo.save(e);

        System.out.println("----------REACHED END OF EVENT CREATE--------");

        return e;
    }

    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Event moveEvent(@RequestBody EventMoveParams params) {

        Event e = eventRepo.findById(params.id).get();

        e.setStart(params.start);
        e.setEnd(params.end);

        eventRepo.save(e);

        return e;
    }

    @PostMapping("/api/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event setColor(@RequestBody SetColorParams params) {

        Event e = eventRepo.findById(params.id).get();
        e.setColor(params.color);
        eventRepo.save(e);

        return e;
    }

    @PostMapping("/api/events/delete")
    Event deleteEvent(@RequestBody EventUpdateParams params) {

        Event e = eventRepo.findById(params.id).get();
        eventRepo.delete(e);
        return e;
    }

    // @GetMapping("/api/studentName")
    // public String studentName() {
    // return "name";
    // }
    // }

    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
        public int sid;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }
    }

    public static class EventUpdateParams {
        private Long id;
        private String text;

        public EventUpdateParams() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }

}
