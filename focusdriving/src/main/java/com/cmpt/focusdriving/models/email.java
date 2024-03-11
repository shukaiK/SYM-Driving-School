package com.cmpt.focusdriving.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class email {
   @Autowired
   private JavaMailSender mailSender;

   public email() {
   }

   public void sendEmail(String toEmail, String subject, String body) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("cmpt276.groupproject@gmail.com");
      message.setTo(toEmail);
      message.setText(body);
      message.setSubject(subject);
      this.mailSender.send(message);
      System.out.println("mail sent");
   }
}
