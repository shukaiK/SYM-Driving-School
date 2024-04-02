package com.cmpt.focusdriving.models.Booking;

import com.cmpt.focusdriving.models.Student.Student;

import java.io.Serializable;
import java.time.*;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking implements Serializable, Comparable<Booking> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;

    @ManyToOne
@JoinColumn(name = "student")
    private Student student;
    
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Booking() {
    };

    public Booking(Student student, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.student = student;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Booking otherBooking) {
        // compare bookings based on start time
        return this.startTime.compareTo(otherBooking.startTime);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

}
