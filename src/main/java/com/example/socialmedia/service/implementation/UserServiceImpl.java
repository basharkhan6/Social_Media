package com.example.socialmedia.service.implementation;

import com.example.socialmedia.exception.DuplicateUserException;
import com.example.socialmedia.exception.ResourceNotFoundException;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean emailExist(String email) {
        return userRepository.existsUserByEmailIgnoreCase(email);
    }

    @Override
    public void createUser(User user) {
        if (emailExist(user.getEmail())) {
            throw new DuplicateUserException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public User findUser(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + email));
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
