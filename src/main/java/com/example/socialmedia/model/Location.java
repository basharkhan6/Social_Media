package com.example.socialmedia.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotEmpty(message = "Location can not be empty")
    private String location;

    public Location() {
    }

    public Location(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String place) {
        this.location = place;
    }
}
