package com.cmpt.focusdriving.models.Student;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String name;
    private String email;
    private String phoneNumber;
    private String requestMessage;
    private String time;
    private String date;

    //location input?
    //any other info?
    
    public Student() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getData() {
        return date;
    }

    public void setData(String data) {
        this.date = data;
    }


    public Student(String name, String email, String phoneNumber, String requestMessage, String time) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.requestMessage = requestMessage;
        this.time = time;
    }

    public Student(String name, String email, String phoneNumber, String requestMessage, String time, String date) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.requestMessage = requestMessage;
        this.time = time;
        this.date = date;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

}


    
