package com.example.socialmedia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "Hello Visitor";
    }

    @GetMapping("/secure/page")
    public String secure() {
        return "This is an secure page!!!";
    }
}
