package com.example.socialmedia.repository;

import com.example.socialmedia.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    boolean existsStatusById(int id);

    List<Status> findAllByUserId(int id);

}
