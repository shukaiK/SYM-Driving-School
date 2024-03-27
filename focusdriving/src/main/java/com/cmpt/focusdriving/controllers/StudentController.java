package com.cmpt.focusdriving.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cmpt.focusdriving.models.email;
import com.cmpt.focusdriving.models.Student.Student;
import com.cmpt.focusdriving.models.Student.StudentRepository;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private email senderService;

    @PostMapping("/html/form")
    public String form(@RequestParam Map<String, String> user, HttpServletResponse response) {
        // Extracting basic information
        String emailString = user.get("email");
        String nameString = user.get("name");
        String phoneString = user.get("phone");
        String addressString = user.get("address");
        String licenseNum = user.get("licenseNum");
        String experienceStr = user.get("experience");

        // Building the list of availabilities
        List<String> availability = new ArrayList<>();
        String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        for (String day : daysOfWeek) {
            String availabilityStatus = user.getOrDefault(day, "Not Available");
            availability.add(day + ": " + availabilityStatus);
        }

        // Constructing the message for email
        StringBuilder messageConcat = new StringBuilder();
        messageConcat.append("Name: ").append(nameString)
                .append("\nEmail: ").append(emailString)
                .append("\nPhoneNumber: ").append(phoneString)
                .append("\nAddress: ").append(addressString)
                .append("\nLicense Number: ").append(licenseNum)
                .append("\nExperience: ").append(experienceStr)
                .append("\n\nAvailability:\n");

        availability.forEach(avail -> messageConcat.append(avail).append("\n"));

        // Sending emails
        senderService.sendEmail("cmpt276.groupproject@gmail.com", "New Request by " + nameString,
                messageConcat.toString());
        senderService.sendEmail(emailString, "Attention: Your request has been sent", "Dear " + nameString
                + ",\nYour request has been sent to our invoice, and we will respond back shortly.");

        // Creating and saving the Student object
        Student student = new Student(nameString, emailString, phoneString, licenseNum, experienceStr, addressString,
                availability);
        studentRepo.save(student);

        // Redirecting to the home page
        return "redirect:/html/home.html";
    }
}

// package com.cmpt.focusdriving.controllers;

// import java.util.Map;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.cmpt.focusdriving.models.email;
// import com.cmpt.focusdriving.models.Student.Student;
// import com.cmpt.focusdriving.models.Student.StudentRepository;

// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.web.bind.annotation.RequestBody;

// @Controller
// public class StudentController {

// @Autowired
// private StudentRepository studentRepo;

// @Autowired
// private email senderService;

// @PostMapping("/student/add")
// public String addStudent(@RequestParam Map<String, String> newStudent) {
// System.out.println("ADD student");
// String name = newStudent.get("name");
// String email=newStudent.get("email");
// String phoneNumber=newStudent.get("phoneNumber");
// String requestMessage=newStudent.get("requestMessage");

// // Student student = new Student(name, email, phoneNumber, requestMessage);
// // studentRepo.save(student);
// return "redirect:/student/submissionComplete";
// }

// @PostMapping({"/userrequest"})
// public String sendEmail(@RequestParam Map<String, String> userid,
// HttpServletResponse response) {
// String emailString = (String)userid.get("email");
// String nameString = (String)userid.get("name");
// String messageString = (String)userid.get("message");
// String phoneString = (String)userid.get("phone");
// String timeString = (String)userid.get("time");
// String messageConcat = nameString + "\n" + emailString + "\n" + phoneString +
// "\n" + timeString + "\n" + messageString;
// senderService.sendEmail("cmpt276.groupproject@gmail.com", "New Request by" +
// nameString, messageConcat); // send email to our selfs as a notfication for a
// new request

// Student student = new
// Student(nameString,emailString,phoneString,messageString,timeString);
// studentRepo.save(student);

// senderService.sendEmail(emailString, "Attention Your request has been sent",
// "Dear " + nameString + "\n" + "your request has been sent to our invoice and
// we will respond back shortly");
// return "redirect:/home.html";
// }

// @GetMapping("/requestview")
// public String showRequests(Model model)
// {
// List<Student> user = studentRepo.findAll();
// model.addAttribute("data", user);
// return "user/requestAction";

// }

// @PostMapping("/confirm")
// public String confrim(@RequestParam Map<String, String> userid,
// HttpServletResponse response)
// {

// int ID = Integer.parseInt(userid.get("ID"));
// List<Student> student = studentRepo.findBySid(ID);
// Student SendEmailConfirmation = student.get(0);
// String emailString = SendEmailConfirmation.getEmail();
// String timeString = SendEmailConfirmation.getTime();
// String nameString = SendEmailConfirmation.getName();
// senderService.sendEmail(emailString, "Your booking is confirmed", "Dear "+
// nameString + "\n\nYour booking has been Confrimed for " + timeString);
// System.out.println(emailString);
// return "redirect:/home.html";
// }

// @PostMapping("/deny")
// public String deny(@RequestParam Map<String, String> userid,
// HttpServletResponse response)
// {

// int ID = Integer.parseInt(userid.get("ID"));
// List<Student> student = studentRepo.findBySid(ID);
// Student SendEmailConfirmation = student.get(0);
// String emailString = SendEmailConfirmation.getEmail();
// String timeString = SendEmailConfirmation.getTime();
// String nameString = SendEmailConfirmation.getName();
// senderService.sendEmail(emailString, "Alternate Booking", "Dear "+ nameString
// + "\n\nYour booking time is full for " + timeString + "\n Here are some
// alternate booking times");
// System.out.println(emailString);
// return "redirect:/home.html";
// }

// //@ModelAttribute Student student

// }
