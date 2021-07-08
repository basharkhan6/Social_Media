package com.example.socialmedia.service.implementation;

import com.example.socialmedia.model.Location;
import com.example.socialmedia.repository.LocationRepository;
import com.example.socialmedia.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
