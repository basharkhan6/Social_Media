package com.example.socialmedia.repository;

import com.example.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByEmailIgnoreCase(String email);

    Optional<User> findByEmailIgnoreCase(String email);

}
