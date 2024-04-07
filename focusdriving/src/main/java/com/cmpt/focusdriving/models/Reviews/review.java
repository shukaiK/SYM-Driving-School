package com.cmpt.focusdriving.models.Reviews;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "review")
public class review implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;

    private String stars;
    private String feedback;
    private String first_name;
    private String last_name;
    private String display;
    private String date;
    

    
    
    public review(String stars, String feedback, String first_name, String last_name, String date) {
        this.stars = stars;
        this.feedback = feedback;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date = date;
        this.display = "hide";
    }

    

    public review(String stars, String feedback, String date) {
        this.stars = stars;
        this.feedback = feedback;
        this.date = date;
        this.first_name = " ";
        this.last_name = " ";
        this.display = "hide";
    }



    public review() {
    }
    public int getRid() {
        return rid;
    }
    public void setRid(int rid) {
        this.rid = rid;
    }
    public String getDisplay() {
        return display;
    }
    public void setDisplay(String display) {
        this.display = display;
    }
    public review(String stars, String feedback) {
        this.stars = stars;
        this.feedback = feedback;
        this.first_name = " ";
        this.last_name = " ";
        this.display = "hide";

    }
    public review(String stars, String feedback, String first_name, String last_name) {
        this.stars = stars;
        this.feedback = feedback;
        this.first_name = first_name;
        this.last_name = last_name;
        this.display = "hide";
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getStars() {
        return stars;
    }
    public void setStars(String stars) {
        this.stars = stars;
    }
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    
}
