package com.cmpt.focusdriving.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cmpt.focusdriving.models.slideshow.imageRepository;
import com.cmpt.focusdriving.models.slideshow.imgUp;

import jakarta.servlet.http.HttpSession;



@Controller
public class imageController {
    
    @Autowired
    private imageRepository imageRepo;

    @Value("${upload.directory}")
    private String uploadDirectory;


    @GetMapping("/")
    public String image() {
        
        return "image";
    }

    @PostMapping("/user/imageUpload")
    public String imageUpload(@RequestParam MultipartFile image, HttpSession session) {

        System.out.println(image.getOriginalFilename());
        imgUp im = new imgUp();
        im.setImageName(image.getOriginalFilename());
        

        imgUp uploadImg =imageRepo.save(im);

        if(uploadImg != null){
            try {

                /* File saveFile = new ClassPathResource("static/img").getFile();
                
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+image.getOriginalFilename());

                Files.copy(image.getInputStream(), path, java.nio.file.StandardCopyOption.REPLACE_EXISTING); */

                File saveDirectory = new File(uploadDirectory);
                if (!saveDirectory.exists()) {
                    saveDirectory.mkdirs(); // Create directories if they don't exist
                }

                Path path = Paths.get(saveDirectory.getAbsolutePath() + File.separator + image.getOriginalFilename());
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        session.setAttribute("message", "Image Uploaded Successfully");
        return "redirect:/html/image.html";
    }
}
