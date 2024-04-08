package com.cmpt.focusdriving.controllers;

import com.cmpt.focusdriving.models.Events.Event;
import com.cmpt.focusdriving.models.Events.EventRepository;
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

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepo;

    @GetMapping("/api/events")
    List<Event> all() {
        return eventRepo.findAll();
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody EventCreateParams params) {

        Event e = new Event();
        e.setStart(params.start);
        e.setEnd(params.end);
        e.setText(params.text);

        eventRepo.save(e);

        return e;
    }

    // @PostMapping("/api/events/update")
    // void updateEventText(@RequestBody EventUpdateParams params) {

    //     Event e = eventRepo.findById(params.id).get();
    //     e.setText(params.text);
    //     eventRepo.save(e);

    //     // return e;
    // }

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

    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
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
