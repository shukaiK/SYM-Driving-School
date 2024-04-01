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

    @GetMapping("/user/bookingview")
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingRepo.findAll();
        model.addAttribute("books", bookings);
        return "user/showbookings";
    }

    @PostMapping("/user/addbooking")
    public String submitBooking(@RequestParam("sid") String sid,
            @RequestParam("date") LocalDate date,
            @RequestParam("startTime") LocalTime startTime,
            @RequestParam("endTime") LocalTime endTime) {
        Student student = (studentRepo.findBySid(Integer.parseInt(sid))).get(0); // Retrieve student from repository

        // create new booking
        Booking booking = new Booking();
        booking.setStudent(student);
        booking.setStartTime(LocalDateTime.of(date, startTime));
        booking.setEndTime(LocalDateTime.of(date, endTime));

        // save new booking
        bookingRepo.save(booking);
        return "redirect:/bookingview";
    }

    // @GetMapping("/editbooking/{bid}")
    // public String showEditForm(@PathVariable("bid") Integer bid, Model model) {
    // Booking booking = (bookingRepo.findByBid(bid)).get(0); // .orElse(null);
    // if (booking == null) {
    // return "error";
    // }
    // model.addAttribute("booking", booking);
    // return "/user/editbooking";
    // }

    // @PostMapping("/user/editinfo/{bid}")
    // public String editBooking(@PathVariable Integer bid, @RequestParam
    // Map<String, String> newinfo,
    // HttpServletResponse response) {
    // Booking booking = (bookingRepo.findByBid(bid)).get(0); // .orElse(nul
    // // booking.setStudent(Student.parseStudent(newinfo.get("student")));
    // // booking.setStartTime(newinfo.get("startTime"));
    // // booking.setEndTime(newinfo.get("endTime"));
    // // bookingRepo.save(booking);
    // // return "redirect:/bookingview";
    // return "/user/dashboard";
    // }

    @PostMapping("/user/deletebooking/{bid}")
    public String deleteBooking(@PathVariable Integer bid,
            HttpServletResponse response) {
        Booking booking = (bookingRepo.findByBid(bid)).get(0); // .orElse(null);x
        bookingRepo.delete(booking);
        return "redirect:/bookingview";
    }

}
