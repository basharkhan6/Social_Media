package com.example.socialmedia.service.implementation;

import com.example.socialmedia.exception.ResourceNotFoundException;
import com.example.socialmedia.model.Status;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.StatusRepository;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(Status status, String email) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + email));

        status.setUser(user);
        statusRepository.save(status);
    }

    @Override
    public void update(Status status, String email) {
        Status statusFound = statusRepository.findById(status.getId())
                .orElseThrow(() -> new ResourceNotFoundException());

        if (!email.equals(statusFound.getUser().getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        status.setUser(statusFound.getUser());
        statusRepository.save(status);
    }

    @Override
    public Status findById(int id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public List<Status> findAllByUserEmail(String email) {
        return statusRepository.findAllByUserEmail(email);
    }

    @Override
    public void delete(int id, String email) {
        Status statusFound = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());

        if (!email.equals(statusFound.getUser().getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        statusRepository.deleteById(id);
    }
}
