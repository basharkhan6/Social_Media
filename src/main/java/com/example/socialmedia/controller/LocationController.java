package com.example.socialmedia.controller;

import com.example.socialmedia.model.Location;
import com.example.socialmedia.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/api/location")
    public List<Location> getAllLocation() {
        return locationService.findAll();
    }
}
