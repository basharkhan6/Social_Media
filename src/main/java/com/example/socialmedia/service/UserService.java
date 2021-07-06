package com.example.socialmedia.service;

import com.example.socialmedia.exception.DuplicateUserException;
import com.example.socialmedia.model.User;

import java.util.List;

public interface UserService {

    boolean emailExist(String email);

    void createUser(User user);

    User findUser(int id);

    User findUser(String email);

    List<User> findAllUser();
}
