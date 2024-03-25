package com.cmpt.focusdriving.models.Booking;

import com.cmpt.focusdriving.models.Student.Student;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "bookings")
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;
    private Student student;
    private String date;
    private String time;

    public Booking() {
    };

    public Booking(Student student, String date, String time) {
        this.student = student;
        this.date = date;
        this.time = time;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setData(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

}
