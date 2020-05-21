package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.model.Response;
import com.supinfo.proj.retailr.apistore.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable(required = false) String username) {
        logger.info("GET on /users/{username} with param " + username);
        if (this.userRepository.existsByUsername(username)) {
            return ResponseEntity.ok().body(this.userRepository.findByUsername(username));
        } else {
            return ResponseEntity.ok().body(new Response("username " + username + " doesn't exist"));
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        logger.info("GET on /users");
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        logger.info("POST on /users/delete/{username} with param : " + username);
        if (this.userRepository.existsByUsername(username)) {
            this.userRepository.delete(this.userRepository.findByUsername(username).get());
            return ResponseEntity.ok().body(new Response("User " + username + " successfully deleted"));
        } else {
            return ResponseEntity.ok().body(new Response("Username " + username + " not found"));
        }
    }
}
