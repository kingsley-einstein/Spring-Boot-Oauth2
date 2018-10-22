package com.security.practice.controllers;

import com.security.practice.repositories.UserRepository;
import com.security.practice.repositories.ProfilePicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

import com.security.practice.models.ProfilePic;

@RestController
@RequestMapping(value = "/api")
public class ProfilePicController {

    @Autowired
    private ProfilePicRepository profileRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping(value = "/user/{id}/upload")
    @ResponseBody
    public ProfilePic upload(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws java.io.IOException {
        return profileRepo.save(new ProfilePic(Base64.getEncoder().encodeToString(file.getBytes()), userRepo.findById(id).get()));
    }

}