package com.example.socialmedia.service;

import com.example.socialmedia.exception.DuplicateUserException;
import com.example.socialmedia.exception.ResourceNotFoundException;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    String email = "bashar@gmail.com";
    String email2 = "burhan@gmail.com";
    User user = new User(email, "Bashar", "xyz12");
    User user2 = new User(email2, "Burhan", "xyz12");

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void emailExist() {
        when(userRepository.existsUserByEmailIgnoreCase(Matchers.anyString())).thenReturn(true);
        assertTrue(userService.emailExist(email));
    }

    @Test
    void createUser() {
        assertThrows(DuplicateUserException.class, () -> {
            when(userRepository.existsUserByEmailIgnoreCase(Matchers.anyString())).thenReturn(true);
            userService.createUser(user);
        });

        assertDoesNotThrow(() -> {
            when(userRepository.existsUserByEmailIgnoreCase(user.getEmail())).thenReturn(false);
            when(userRepository.save(user)).thenReturn(user);
            userService.createUser(user);
        });
    }

    @Test
    void findUserWithId() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        assertEquals(user, userService.findUser(user.getId()));

        assertThrows(ResourceNotFoundException.class, () -> {
            when(userRepository.findById(user2.getId())).thenReturn(Optional.empty());
            userService.findUser(user2.getId());
        });
    }

    @Test
    void findUserWithEmail() {
        when(userRepository.findByEmailIgnoreCase(user.getEmail())).thenReturn(Optional.of(user));
        assertEquals(user, userService.findUser(user.getEmail()));

        assertThrows(ResourceNotFoundException.class, () -> {
            when(userRepository.findByEmailIgnoreCase(user2.getEmail())).thenReturn(Optional.empty());
            userService.findUser(user2.getEmail());
        });
    }

    @Test
    void findAllUser() {
    }
}