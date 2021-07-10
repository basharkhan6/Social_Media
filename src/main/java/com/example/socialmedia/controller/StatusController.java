package com.example.socialmedia.controller;

import com.example.socialmedia.model.Status;
import com.example.socialmedia.service.StatusService;
import com.example.socialmedia.service.implementation.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController()
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/api/status")
    public List<Status> getAllPublicStatus() {
        return statusService.findAllPublicStatus();
    }

    @GetMapping("/api/status/{id}")
    public Status getStatus(@PathVariable int id) {
        return statusService.findById(id);
    }

    @PostMapping("/api/status")
    public Status postStatus(Principal principal, @RequestBody @Valid Status status) {
        return statusService.save(principal.getName(), status);
    }

    @PutMapping("/api/status")
    public Status updateStatus(Principal principal, @RequestBody @Valid Status status) {
        return statusService.update(principal.getName(), status);
    }

    @DeleteMapping("/api/status/{id}")
    public ResponseEntity postStatus(Principal principal, @PathVariable int id) {
        statusService.delete(principal.getName(), id);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/status/findByUserId")
    public List<Status> getAllPublicStatusByUserId(@RequestParam int userId) {
        return statusService.findAllPublicStatusByUserId(userId);
    }

    @GetMapping("/api/status/findByUser")
    public List<Status> getAllStatusByUser(Principal principal) {
        return statusService.findAllByUserEmail(principal.getName());
    }

}
