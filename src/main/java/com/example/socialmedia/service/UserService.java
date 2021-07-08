package com.example.socialmedia.service;

import com.example.socialmedia.model.User;
import com.example.socialmedia.model.dto.PasswordDto;
import com.example.socialmedia.model.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    boolean emailExist(String email);

    User createUser(User user);

    User findUser(int id);

    User findUser(String email);

    List<User> findAllUser();

    User updateUser(String email, UserUpdateDto userUpdateDto);

    void changePassword(String email, PasswordDto passwordDto);
}
