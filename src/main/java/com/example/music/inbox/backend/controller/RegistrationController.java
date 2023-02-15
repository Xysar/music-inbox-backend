package com.example.music.inbox.backend.controller;

import com.example.music.inbox.backend.entity.User;
import com.example.music.inbox.backend.model.UserModel;
import com.example.music.inbox.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel){
 User user = userService.registerUser(userModel);
 return "Success";
}
}
