package com.cmpt.focusdriving.controllers;

//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmpt.focusdriving.models.User.User;
import com.cmpt.focusdriving.models.User.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserManagementController {  
    
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/admin/manageInstructors")
    public String getAllStudents(Model model){
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
        //FOR EACH STUDENT CHECK IF ASSIGNED TO DELETED USER AND RETURN TO PENDING
        // If exists, delete the student
        userRepo.deleteById(uid);
        // Set HTTP response status to 200 OK
        response.setStatus(HttpServletResponse.SC_OK);
        // Redirect to a confirmation page or back to the list
        
    } 
    return "redirect:/admin/manageInstructors";
}


@GetMapping("/admin/editUser/{uid}")
public String editStudent(@PathVariable("uid") int uid, Model model) {
    // Directly fetching the student without Optional
    User user = userRepo.findById(uid).orElse(null);
    if (user != null) {
        model.addAttribute("user", user);
        return "admin/editUser";
    } else {
        // Redirect or handle the case where the student is not found
        return "redirect:/admin/dashboard";
    }
}

// @PostMapping("/student/update")
// public String updateStudent(@ModelAttribute Student student) {
//     // Assuming you have a setter in your Student model for all fields that can be updated
//     studentRepo.save(student); // Saves the changes to the student
//     return "redirect:/student/view"; // Redirect back to the student listing page
// }


}


