package com.cmpt.focusdriving.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt.focusdriving.models.Reviews.review;
import com.cmpt.focusdriving.models.Reviews.reviewRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class reviewController
{
    @Autowired
    private reviewRepository reviewRepo;

    @PostMapping("/html/review")
    public String reviewForm(@RequestParam Map<String, String> user)
    {
        String StarString = user.get("star_val");

        String feedbackString = user.get("feedback");
        String firstString = user.get("first");
        String lastString = user.get("last");
        String DateString = user.get("date");
        review reviews;
        if (firstString.isEmpty() && lastString.isEmpty())
        {
            reviews = new review(StarString,feedbackString,DateString);
        }
        else if(lastString.isEmpty())
        {
           reviews = new review(StarString,feedbackString,firstString," ",DateString);

        }
        else if (firstString.isEmpty())
        {
            reviews = new review(StarString,feedbackString," ",lastString,DateString);

        }
        else
        {
            reviews = new review(StarString,feedbackString,firstString,lastString,DateString);
        }


        
        reviewRepo.save(reviews);
        return "redirect:/html/home.html";
    }

    @GetMapping("/admin/privatereviews")
    public String privateReviews(Model model) 
    {
        List<review> reviews = reviewRepo.findByDisplay("hide");
        model.addAttribute("reviews",reviews);
        List<review> reviewVisable = reviewRepo.findByDisplay("show");
        model.addAttribute("reviewVisable",reviewVisable);
       return "user/ownerReviews";
    }

    @PostMapping("/admin/reviews")
    public String postMethodName(@RequestParam Map<String,String> review) {
         String optionString = review.get("display_review");
         int getID =  Integer.parseInt(review.get("rid"));
        List<review> getobject = reviewRepo.findById(getID);
        review reviews = getobject.get(0);
        if (optionString.equals("Remove"))
        {
            
            reviewRepo.delete(reviews);
        }
        else
        {
            
             reviews.setDisplay(optionString);
             reviewRepo.save(reviews);

        }
        return "redirect:/admin/privatereviews";
    }

    @GetMapping("/html/publicreviews")
    public String publicReviews(Model model) 
    {
        List<review> reviews = reviewRepo.findByDisplay("show");
        model.addAttribute("reviews",reviews);
       return "user/UserReview";
    }
    
    

}
