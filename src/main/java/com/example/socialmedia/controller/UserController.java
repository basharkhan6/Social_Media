package com.example.socialmedia.controller;

import com.example.socialmedia.model.User;
import com.example.socialmedia.model.dto.PasswordDto;
import com.example.socialmedia.model.dto.UserUpdateDto;
import com.example.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/api/userInfo")
    public User getUser(Principal principal) {
        return userService.findUser(principal.getName());
    }

    @GetMapping("/api/userInfo/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.findUser(id);
    }

    @PostMapping("/api/registration")
    public User registerUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @PutMapping("/api/userInfo")
    public User updateUser(Principal principal, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(principal.getName(), userUpdateDto);
    }

    @PostMapping("/api/changePassword")
    public ResponseEntity changePassword(Principal principal, @RequestBody @Valid PasswordDto passwordDto) {
        userService.changePassword(principal.getName(), passwordDto);
        return new ResponseEntity(null, HttpStatus.ACCEPTED);
    }

}
