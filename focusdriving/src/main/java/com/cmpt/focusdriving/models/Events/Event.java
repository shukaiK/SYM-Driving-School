package com.cmpt.focusdriving.models.Events;

import com.cmpt.focusdriving.models.Student.Student;

import java.io.Serializable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String text;

    @Column(name = "event_start")
    LocalDateTime start;

    @Column(name = "event_end")
    LocalDateTime end;

    String color;

    Integer sid;

    String instructorName;

    // Student student;

    public Event() {

    }

    public Event(LocalDateTime start, LocalDateTime end, String text, String instructorName) {
        this.start = start;
        this.end = end;
        this.text = text;
        this.instructorName = instructorName;
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    // public Student getStudent() {
    // return student;
    // }

    // public void setStudent(Student student) {
    // this.student = student;
    // }
}
