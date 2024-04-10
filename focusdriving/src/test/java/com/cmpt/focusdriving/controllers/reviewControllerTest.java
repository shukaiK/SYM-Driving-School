package com.cmpt.focusdriving.controllers;

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

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@WebMvcTest(reviewController.class)
public class reviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private reviewRepository reviewRepo;

    @BeforeAll
    static void setup() {
        System.out.println("Setting up");
    }

    @Test
    public void testReviewFormSubmission() throws Exception {
        // Simulate form submission
        mockMvc.perform(post("/html/review")
                .param("star_val", "5")
                .param("feedback", "Great service!")
                .param("first", "John")
                .param("last", "Doe")
                .param("date", "2023-04-01"))
                .andExpect(status().isOk());

        // Verify that the review was saved
        // Note: In a real test, you'd use ArgumentCaptor to capture the review object and verify its properties
    }

    @Test
    void testGetAllPrivateReviews() throws Exception {
        this.mockMvc.perform(get("/admin/privatereviews"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html"));
    }

    @Test
    void testPostReview() throws Exception {
        review requestBody = new review();
        requestBody.setFirst_name("hello");

        Object objectMapper;
        mockMvc.perform(post("/html/review")
            .contentType("application/json")
            .content(((Object) objectMapper).writeValueAsString(requestBody)))
            .andExpect(status().is3xxRedirection()); // Assuming it redirects upon successful review submission
    }

    @Test
    void testGetAllPublicReviews() throws Exception {
        this.mockMvc.perform(get("/html/publicreviews"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html"));
    }

}
