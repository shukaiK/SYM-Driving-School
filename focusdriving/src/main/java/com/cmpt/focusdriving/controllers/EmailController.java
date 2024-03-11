package com.cmpt.focusdriving.controllers;


import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt.focusdriving.models.email;

@Controller
public class EmailController {
   @Autowired
   private email senderService;

   public EmailController() {
   }

   @PostMapping({"/userrequest"})
   public String sendEmail(@RequestParam Map<String, String> userid, HttpServletResponse response) {
      String sendString = (String)userid.get("email");
      String subjectString = (String)userid.get("subject");
      String messageString = (String)userid.get("message");
      senderService.sendEmail(sendString, subjectString, messageString);
      return "redirect:/home.html";
   }
}

