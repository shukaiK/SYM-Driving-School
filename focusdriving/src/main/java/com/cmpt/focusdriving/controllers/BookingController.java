package com.cmpt.focusdriving.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cmpt.focusdriving.models.Booking.Booking;
import com.cmpt.focusdriving.models.Booking.BookingRepository;
import com.cmpt.focusdriving.models.Student.Student;
import com.cmpt.focusdriving.models.Student.StudentRepository;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/bookingview")
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingRepo.findAll();
        model.addAttribute("books", bookings);
        return "user/showbookings";
    }

    @PostMapping("/user/addbooking")
    public void submitBooking(@RequestParam("sid") int sid,
            @RequestParam("date") LocalDate date,
            @RequestParam("startTime") LocalTime startTime,
            @RequestParam("endTime") LocalTime endTime) {
        Student student = (studentRepo.findBySid(sid)).get(0); // Retrieve student from repository

        // create new booking
        Booking booking = new Booking();
        booking.setStudent(student);
        booking.setStartTime(LocalDateTime.of(date, startTime));
        booking.setEndTime(LocalDateTime.of(date, endTime));

        // save new booking
        bookingRepo.save(booking);
    }

}
