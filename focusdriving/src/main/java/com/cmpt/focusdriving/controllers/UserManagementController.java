package com.cmpt.focusdriving.controllers;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmpt.focusdriving.models.Booking.Booking;
import com.cmpt.focusdriving.models.Booking.BookingRepository;
import com.cmpt.focusdriving.models.Security.PasswordValidator;
import com.cmpt.focusdriving.models.Student.Student;
import com.cmpt.focusdriving.models.Student.StudentRepository;
import com.cmpt.focusdriving.models.User.User;
import com.cmpt.focusdriving.models.User.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserManagementController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private BookingRepository bookingRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordValidator passwordValidator;

    

    @GetMapping("/admin/manageInstructors")
    public String getAllStudents(Model model) {
        System.out.println("Getting all users");
        // get all users from database
        List<User> users = userRepo.findByRole("USER");
        // end of database call
        model.addAttribute("users", users);
        return "user/manageInstructors";
    }

    @PostMapping("/admin/deleteUser/{uid}")
public String deleteStudent(@PathVariable("uid") int uid, HttpServletResponse response) {
    System.out.println("Deleting student with UID: " + uid);
    // Check if a student with the given UID exists
    boolean exists = userRepo.existsById(uid);
    if (exists) {
        User user = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        // Fetch students assigned to this instructor
        List<Student> studentsToReset = studentRepo.findByInstructor(user.getName());

        
        // Iterate through each student and reset their instructor to "Pending"
        for (Student student : studentsToReset) {
            if (student.getInstructor().equals(user.getName())) {
                student.setInstructor("Pending"); // Assuming there's a setInstructor method in your Student class
                studentRepo.save(student); // Save the updated student back to the repository
            }
        }

        List<Booking> bookingsToReset=  bookingRepo.findByStudent_InstructorContaining(user.getName());
        for (Booking booking : bookingsToReset) {
            if (booking.getStudent().getInstructor().equals(user.getName())) {
                bookingRepo.deleteById(booking.getBid()); // Save the updated student back to the repository
            }
        }

        

        // After resetting students, delete the instructor
        userRepo.deleteById(uid);
        // Set HTTP response status to 200 OK
        response.setStatus(HttpServletResponse.SC_OK);
        // Redirect to a confirmation page or back to the list
    }
    return "redirect:/admin/manageInstructors"; // Make sure this is the correct redirect path
}



  @PostMapping("/admin/updateUser")
public String updateUser(@RequestParam("uid") int userId, @RequestParam("password") String newPassword,
                         RedirectAttributes redirectAttributes) {
    if (!passwordValidator.validate(newPassword)) {
        redirectAttributes.addFlashAttribute("error", "Invalid password, please try again!");
    } else {
        Optional<User> userOptional = userRepo.findByUid(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(user);
        }
    }
    return "redirect:/admin/manageInstructors";
}


}
