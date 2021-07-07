package com.example.socialmedia.controller;

import com.example.socialmedia.model.Status;
import com.example.socialmedia.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController()
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/api/status")
    public List<Status> getAllStatus() {
        return statusService.findAll();
    }

    @GetMapping("/api/status/{id}")
    public Status getStatus(@PathVariable int id) {
        return statusService.findById(id);
    }

    @PostMapping("/api/status")
    public void postStatus(@RequestBody @Valid Status status, Principal principal) {
        statusService.save(status, principal.getName());
    }

    @PutMapping("/api/status")
    public void updateStatus(@RequestBody @Valid Status status, Principal principal) {
        statusService.update(status, principal.getName());
    }

    @DeleteMapping("/api/status/{id}")
    public void postStatus(@PathVariable int id, Principal principal) {
        statusService.delete(id, principal.getName());
    }

    @GetMapping("/api/status/findByUser")
    public List<Status> getAllStatus(Principal principal) {
        return statusService.findAllByUserEmail(principal.getName());
    }
}
