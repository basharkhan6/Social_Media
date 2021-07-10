package com.example.socialmedia.service.implementation;

import com.example.socialmedia.exception.ResourceNotFoundException;
import com.example.socialmedia.model.Status;
import com.example.socialmedia.model.User;
import com.example.socialmedia.model.enumeration.PrivacyEnum;
import com.example.socialmedia.repository.StatusRepository;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusServiceImpl.class);

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Status save(String email, Status status) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + email));

        status.setUser(user);
        LOGGER.trace("Saving new Status {}", status);
        return statusRepository.save(status);
    }

    @Override
    public Status update(String email, Status status) {
        Status statusFound = findById(status.getId());

        if (!email.equals(statusFound.getUser().getEmail())) {
            LOGGER.debug("Illegal Attempt to update status of {} by {}", statusFound.getUser().getEmail(), email);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        status.setUser(statusFound.getUser());      // user should not change
        LOGGER.trace("Updating Status {}", status);
        return statusRepository.save(status);
    }

    @Override
    public Status findById(int id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.trace("Status not found with id {}", id);
                    return new ResourceNotFoundException();
                });
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public void delete(String email, int id) {
        Status statusFound = findById(id);

        if (!email.equals(statusFound.getUser().getEmail())) {
            LOGGER.debug("Illegal Attempt to delete status of {} by {}", statusFound.getUser().getEmail(), email);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        LOGGER.info("Deleting Status {}", id);
        statusRepository.deleteById(id);
    }

    @Override
    public List<Status> findAllByUserId(int userId) {
        return statusRepository.findAllByUserIdOrderByUpdatedAtDesc(userId);
    }

    @Override
    public List<Status> findAllPublicStatus() {
        return statusRepository.findAllByPrivacyOrderByUpdatedAtDesc(PrivacyEnum.PUBLIC);
    }

    @Override
    public List<Status> findAllPublicStatusByUserId(int userId) {
        return statusRepository.findAllByPrivacyAndUserIdOrderByUpdatedAtDesc(PrivacyEnum.PUBLIC, userId);
    }

    @Override
    public List<Status> findAllByUserEmail(String email) {
        return statusRepository.findAllByUserEmailOrderByUpdatedAtDesc(email);
    }

}
