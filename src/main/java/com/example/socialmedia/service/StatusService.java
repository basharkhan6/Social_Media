package com.example.socialmedia.service;

import com.example.socialmedia.model.Status;

import java.util.List;

public interface StatusService {

    Status save(String email, Status status);

    Status update(String email, Status status);

    Status findById(int id);

    List<Status> findAll();

    void delete(String email, int id);

    List<Status> findAllByUserId(int userId);

    List<Status> findAllPublicStatus();

    List<Status> findAllPublicStatusByUserId(int userId);

    List<Status> findAllByUserEmail(String email);

}
