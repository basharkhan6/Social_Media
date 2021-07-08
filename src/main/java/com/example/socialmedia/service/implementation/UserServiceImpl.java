package com.example.socialmedia.service.implementation;

import com.example.socialmedia.exception.DuplicateUserException;
import com.example.socialmedia.exception.IncorrectPasswordException;
import com.example.socialmedia.exception.ResourceNotFoundException;
import com.example.socialmedia.model.User;
import com.example.socialmedia.model.dto.PasswordDto;
import com.example.socialmedia.model.dto.UserUpdateDto;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean emailExist(String email) {
        return userRepository.existsUserByEmailIgnoreCase(email);
    }

    @Override
    public User createUser(User user) {
        if (emailExist(user.getEmail())) {
            LOGGER.debug("User already exist {}", user.getEmail());
            throw new DuplicateUserException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        LOGGER.trace("Saving user {}", user);
        return userRepository.save(user);
    }

    @Override
    public User findUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.trace("User id not found {}", id);
                    return new UsernameNotFoundException("No user found with id: " + id);
                });
    }

    @Override
    public User findUser(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> {
                    LOGGER.trace("User Email not found {}", email);
                    return new UsernameNotFoundException("No user found with email: " + email);
                });
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String email, UserUpdateDto userUpdateDto) {
        if (!email.equals(userUpdateDto.getEmail())) {
            LOGGER.debug("Illegal attempt to update user {} by user {}", userUpdateDto.getEmail(), email);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User oldUser = findUser(email);
        oldUser.setName(userUpdateDto.getName());     // password should not updated here

        LOGGER.trace("Saving user {}", oldUser);
        return userRepository.save(oldUser);
    }

    @Override
    public void changePassword(String email, PasswordDto passwordDto) {
        User userFound = findUser(email);
        if (userFound.getPassword().equals(passwordDto.getOldPassword())) {
            userFound.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));

            LOGGER.trace("Saving user {}", userFound);
            userRepository.save(userFound);
        } else {
            LOGGER.debug("Failed to changing password of {}. Old Password mismatch", email);
            throw new IncorrectPasswordException();
        }
    }

}
