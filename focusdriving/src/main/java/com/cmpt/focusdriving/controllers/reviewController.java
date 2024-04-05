package com.cmpt.focusdriving.controllers;

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
        review reviews;
        if (firstString.isEmpty() && lastString.isEmpty())
        {
            reviews = new review(StarString,feedbackString);
        }
        else if(lastString.isEmpty())
        {
           reviews = new review(StarString,feedbackString,firstString," ");

        }
        else if (firstString.isEmpty())
        {
            reviews = new review(StarString,feedbackString," ",lastString);

        }
        else
        {
            reviews = new review(StarString,feedbackString,firstString,lastString);
        }


        
        reviewRepo.save(reviews);
        return "redirect:/html/home.html";
    }

    @GetMapping("/admin/privatereviews")
    public String privateReviews(Model model) 
    {
        List<review> reviews = reviewRepo.findByDisplay("hide");
        model.addAttribute("reviews",reviews);
       return "user/ownerReviews";
    }

    @PostMapping("/admin/reviews")
    public String postMethodName(@RequestParam Map<String,String> review) {
        // String optionString = review.get("display_review");
        // int getID =  Integer.parseInt(review.get("rid"));
        // List<review> getobject = reviewRepo.findById(getID);
        // if (optionString.equals("Remove"))
        // {
        //     review reviews = getobject.get(0);
        //     reviewRepo.delete(reviews);
        // }
        // else
        // {
        //     review reviews = getobject.get(0);
        //     reviews.setDisplay(optionString);

        // }
        return "redirect:/admin/privatereviews";
    }
    
    

}
