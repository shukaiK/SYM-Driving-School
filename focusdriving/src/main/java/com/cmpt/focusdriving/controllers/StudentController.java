package com.cmpt.focusdriving.controllers;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cmpt.focusdriving.models.email;
import com.cmpt.focusdriving.models.Student.Student;
import com.cmpt.focusdriving.models.Student.StudentRepository;
import com.cmpt.focusdriving.models.User.User;
import com.cmpt.focusdriving.models.User.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private email senderService;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }

        // Return null or handle the case where no user is authenticated
        return null;
    }

    @PostMapping("/html/form")
    public String form(@RequestParam Map<String, String> user, HttpServletResponse response) {
        // Extracting basic information
        String emailString = user.get("email");
        String nameFString = user.get("name");
        String nameLString = user.get("name1");
        String phoneString = user.get("phone");
        String addressString = user.get("address");
        String licenseNum = user.get("licenseNum");
        String experienceStr = user.get("experience");
        String nameString = nameFString + " " + nameLString;
        // Building the list of availabilities
        List<String> availability = new ArrayList<>();
        String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        for (String day : daysOfWeek) {
            String availabilityStatus = user.getOrDefault(day, "Not Available");
            availability.add(day + ":   " + "\n" + availabilityStatus);
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

    @GetMapping("/admin/pending")
    public String getMethodName(Model model) {
        List<Student> students = studentRepo.findAll();
        List<User> users = userRepo.findAll();
        model.addAttribute("students", students);
        model.addAttribute("users", users);

        return "user/requestAction";
    }

    @PostMapping("/admin/assignStudent")
    public String assign(@RequestParam Map<String, String> submission, HttpServletResponse response,
            @ModelAttribute Student student) {

        String instructorString = submission.get("instructors");
        int ID = Integer.parseInt(submission.get("ID"));
        List<Student> students = studentRepo.findBySid(ID);

        // if(students.get(0).getInstructor()!=instructorString){
        // List<Booking> bookingsToReset=
        // bookingRepo.findByStudent_InstructorContaining(students.get(0).getInstructor());
        // for (Booking booking : bookingsToReset) {
        // if
        // (booking.getStudent().getInstructor().equals(students.get(0).getInstructor()))
        // {
        // bookingRepo.deleteById(booking.getBid()); // Save the updated student back to
        // the repository
        // }
        // }
        // }

        Student SendEmailConfirmation = students.get(0);
        String compare = "Remove";
        if (compare.equals(instructorString)) {
            String getName = SendEmailConfirmation.getName();
            String getEmail = SendEmailConfirmation.getEmail();
            senderService.sendEmail(getEmail, "Alternate Booking",
                    "Dear " + getName + "\n\nYour booking time is full for the availabitly you have sent. ");
            studentRepo.delete(SendEmailConfirmation);
        } else {
            SendEmailConfirmation.setInstructor(instructorString);
            studentRepo.save(SendEmailConfirmation); // Save changes including setting instructor
        }
        return "redirect:/admin/pending";
    }

    @GetMapping("/user/viewAssignedStudents")
    public String getAssignedStudents(Model model) {
        System.out.println("Getting all students");
        // get all students from the database
        List<Student> students = studentRepo.findByInstructor(getCurrentUsername());
        // end of database call
        model.addAttribute("students", students);
        return "user/viewAssignedStudents";
    }
}
