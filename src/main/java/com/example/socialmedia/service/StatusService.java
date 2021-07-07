package com.example.socialmedia.service;

import com.example.socialmedia.model.Status;

import java.util.List;

public interface StatusService {

    void save(Status status, String email);

    void update(Status status, String email);

    Status findById(int id);

    List<Status> findAll();

    List<Status> findAllByUserEmail(String email);

    void delete(int id, String email);

}
