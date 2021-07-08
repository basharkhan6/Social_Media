package com.example.socialmedia.model.dto;

public class UserUpdateDto {

    public String name;

    public String email;

    public UserUpdateDto() {
    }

    public UserUpdateDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
