package com.cmpt.focusdriving.controllers;

import java.util.Map;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt.focusdriving.models.Student;
import com.cmpt.focusdriving.models.StudentRepository;




@Controller
public class StudentController {  
    
    @Autowired
    private StudentRepository studentRepo;
    

    @PostMapping("/student/add")
    public String addStudent(@RequestParam Map<String, String> newStudent) {
        System.out.println("ADD student");
        String name = newStudent.get("name");
        String email=newStudent.get("email");
        String phoneNumber=newStudent.get("phoneNumber");
        String requestMessage=newStudent.get("requestMessage");



        Student student = new Student(name, email, phoneNumber, requestMessage);
        studentRepo.save(student);
        return "redirect:/student/submissionComplete";
    }
}