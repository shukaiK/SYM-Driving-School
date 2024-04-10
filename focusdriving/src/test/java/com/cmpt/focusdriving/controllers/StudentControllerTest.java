package com.cmpt.focusdriving.controllers;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.cmpt.focusdriving.models.email;
import com.cmpt.focusdriving.models.Events.EventRepository;
import com.cmpt.focusdriving.models.Student.Student;
import com.cmpt.focusdriving.models.Student.StudentRepository;
import com.cmpt.focusdriving.models.User.UserRepository;
import com.cmpt.focusdriving.models.Reviews.review;
import com.cmpt.focusdriving.models.Reviews.reviewRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private email senderService;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private StudentController studentController;
 private void assertEquals(String string, String result) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }

    @Test
    void testForm() {
        // Arrange
        Map<String, String> userData = new HashMap<>();
        // Populate userData with required data
        // ...

        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        String result = studentController.form(userData, response);

        // Assert
        // Verify that the save method of studentRepository is called
        verify(studentRepository, times(1)).save(any(Student.class));
        // Verify that the sendEmail method of senderService is called twice
        verify(senderService, times(2)).sendEmail(anyString(), anyString(), anyString());
        // Assert the redirection result
        assertEquals("redirect:/html/home.html", result);
    }

   

    @Test
    void testGetMethodName() {
        // Arrange
        Model model = mock(Model.class);

        // Act
        String result = studentController.getMethodName(model);

        // Assert
        // Verify that findAll method of studentRepository and userRepository are called once
        verify(studentRepository, times(1)).findAll();
        verify(userRepository, times(1)).findAll();
        // Verify that addAttribute method of model is called twice
        verify(model, times(2)).addAttribute(anyString(), any());
        // Assert the returned view name
        assertEquals("user/requestAction", result);
    }

    @Test
    void testStudentFormPost() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "test@example.com");
        requestBody.put("name", "John");
        requestBody.put("name1", "Doe");
        requestBody.put("phone", "1234567890");
        requestBody.put("address", "123 Main St");
        requestBody.put("licenseNum", "ABC123");
        requestBody.put("experience", "Beginner");
        requestBody.put("Monday", "Available");
        requestBody.put("Tuesday", "Not Available");
        requestBody.put("Wednesday", "Available");
        requestBody.put("Thursday", "Not Available");
        requestBody.put("Friday", "Available");
        requestBody.put("Saturday", "Available");
        requestBody.put("Sunday", "Not Available");

        ResultActions resultActions = mockMvc.perform(post("/html/form")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", requestBody.get("email"))
                .param("name", requestBody.get("name"))
                .param("name1", requestBody.get("name1"))
                .param("phone", requestBody.get("phone"))
                .param("address", requestBody.get("address"))
                .param("licenseNum", requestBody.get("licenseNum"))
                .param("experience", requestBody.get("experience"))
                .param("Monday", requestBody.get("Monday"))
                .param("Tuesday", requestBody.get("Tuesday"))
                .param("Wednesday", requestBody.get("Wednesday"))
                .param("Thursday", requestBody.get("Thursday"))
                .param("Friday", requestBody.get("Friday"))
                .param("Saturday", requestBody.get("Saturday"))
                .param("Sunday", requestBody.get("Sunday")));

        resultActions.andExpect(status().is3xxRedirection()) 
                     .andExpect(redirectedUrl("/html/home.html")); 
    }

    // You can write similar tests for other methods in the controller class
}

