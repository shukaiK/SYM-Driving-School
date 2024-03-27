package com.cmpt.focusdriving.models.Student;

import jakarta.persistence.*;

@Entity
@Table(name = "studentAvailability")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String name;
    private String email;
    private String phoneNumber;
    private String licenseNum;
    private String experience;
    private String address;
   
    private String Monday;
    private String Tuesday;
    private String Wednesday;
    private String Thursday;
    private String Friday;
    private String Saturday;
    private String Sunday;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, String email, String phoneNumber, String licenseNum, String experience, String address,
            String monday, String tuesday, String wednesday, String thursday, String friday, String saturday,
            String sunday) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.licenseNum = licenseNum;
        this.experience = experience;
        this.address = address;
        Monday = monday;
        Tuesday = tuesday;
        Wednesday = wednesday;
        Thursday = thursday;
        Friday = friday;
        Saturday = saturday;
        Sunday = sunday;
    }

    public Student(String name, String email, String phoneNumber, String monday,
            String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
      
        Monday = monday;
        Tuesday = tuesday;
        Wednesday = wednesday;
        Thursday = thursday;
        Friday = friday;
        Saturday = saturday;
        Sunday = sunday;
    }
    
    public Student() {
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
  
    public String getMonday() {
        return Monday;
    }
    public void setMonday(String monday) {
        Monday = monday;
    }
    public String getTuesday() {
        return Tuesday;
    }
    public void setTuesday(String tuesday) {
        Tuesday = tuesday;
    }
    public String getWednesday() {
        return Wednesday;
    }
    public void setWednesday(String wednesday) {
        Wednesday = wednesday;
    }
    public String getThursday() {
        return Thursday;
    }
    public void setThursday(String thursday) {
        Thursday = thursday;
    }
    public String getFriday() {
        return Friday;
    }
    public void setFriday(String friday) {
        Friday = friday;
    }
    public String getSaturday() {
        return Saturday;
    }
    public void setSaturday(String saturday) {
        Saturday = saturday;
    }
    public String getSunday() {
        return Sunday;
    }
    public void setSunday(String sunday) {
        Sunday = sunday;
    }

    
}


    
