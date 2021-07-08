package com.example.socialmedia.model.dto;

import javax.validation.constraints.Min;

public class PasswordDto {

    @Min(5)
    public String oldPassword;

    @Min(5)
    public String newPassword;

    public PasswordDto() {
    }

    public PasswordDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

}
