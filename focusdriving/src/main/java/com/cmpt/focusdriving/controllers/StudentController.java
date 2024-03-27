package com.cmpt.focusdriving.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;


import com.cmpt.focusdriving.models.email;
import com.cmpt.focusdriving.models.Student.Student;
import com.cmpt.focusdriving.models.Student.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private email senderService;

    // sendinformation to the database
    @PostMapping("/html/form")
    public String form(@RequestParam Map<String, String> user, HttpServletResponse response) {
        String emailString = (String)user.get("email");
        String nameString = (String)user.get("name");
        String phoneString = (String)user.get("phone");
        String addressString = (String)user.get("address");
        String licenseNum =  (String)user.get("licenseNum");
        String experienceStr =  (String)user.get("experience");
        String Monday = (String)user.get("Monday");
        String Tuesday = (String)user.get("Tuesday");
        String Wednesday = (String)user.get("Wednesday");
        String Thursday = (String)user.get("Thursday");
        String Friday = (String)user.get("Friday");
        String Saturday = (String)user.get("Saturday");
        String Sunday = (String)user.get("Sunday");
        
        String messageConcat = nameString + "\nEmail: " + emailString + "\nPhoneNumber:" + phoneString + "\nAddress: " + addressString +  "\nExperience: " + experienceStr + "\nLicense: " + licenseNum + "\n";
        String dates = Monday + "\n" + Tuesday + "\n" + Wednesday + "\n" + Thursday + "\n" + Friday + "\n" + Saturday + "\n" + Sunday + "\n";
        senderService.sendEmail("cmpt276.groupproject@gmail.com", "New Request by " + nameString,messageConcat+dates);
        senderService.sendEmail(emailString, "Attention Your request has been sent", "Dear " + nameString + "\n" + "your request has been sent to our invoice and we will respond back shortly");
        Student student = new Student(nameString,emailString,phoneString,licenseNum,experienceStr,addressString,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday);
        studentRepo.save(student);
        return "redirect:/html/home.html";
    }

   
    
}