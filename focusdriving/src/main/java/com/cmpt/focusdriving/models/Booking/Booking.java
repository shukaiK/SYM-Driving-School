package com.cmpt.focusdriving.models.Booking;

import com.cmpt.focusdriving.models.Student.Student;

import java.io.Serializable;
import java.util.Date;
import java.time.*;

import org.springframework.format.annotation.DateTimeFormat;
// import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templateresolver.ITemplateResolver;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking implements Serializable, Comparable<Booking> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;
    private Student student;
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    // private Date date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public Booking() {
    };

    public Booking(Student student, LocalDateTime startTime, LocalDateTime endTime) {
        this.student = student;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Booking otherBooking) {
        // compare bookings based on start time
        return this.startTime.compareTo(otherBooking.startTime);
    }

    // constructor
    // public Booking(Student student, LocalDateTime startTime, LocalDateTime
    // endTime) {
    // this.student = student;
    // this.startTime = startTime;
    // this.endTime = endTime;
    // }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

}
