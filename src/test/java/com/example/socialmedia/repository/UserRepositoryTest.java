package com.example.socialmedia.repository;

import com.example.socialmedia.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    private String email = "bashar@gmail.com";
    User user = new User(email, "Bashar", "xyz12");


    @Test
    void existsUserByEmailIgnoreCase() {
        testEntityManager.persistAndFlush(user);

        assertTrue(userRepository.existsUserByEmailIgnoreCase(email));
    }

    @Test
    void findByEmailIgnoreCase() {
        testEntityManager.persistAndFlush(user);

        User found = userRepository.findByEmailIgnoreCase(email.toUpperCase()).orElse(new User());
        assertNotNull(found.getId());
    }
}
