package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.entity.Home;
import com.supinfo.proj.retailr.apistore.data.entity.User;
import com.supinfo.proj.retailr.apistore.data.model.AuthenticationRequest;
import com.supinfo.proj.retailr.apistore.data.model.AuthenticationResponse;
import com.supinfo.proj.retailr.apistore.data.model.Response;
import com.supinfo.proj.retailr.apistore.service.UserDetailServiceImpl;
import com.supinfo.proj.retailr.apistore.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private Home home;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/")
    public Home getHome() {
        logger.info("GET on /");
        return home;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthenticationResponse(null, "Invalid username or password"));
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        logger.info("User " + authenticationRequest.getUsername() + " authenticated");
        return ResponseEntity.ok(new AuthenticationResponse(jwt, null));
    }

    @PostMapping("/user/create")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user, BindingResult result) {
        logger.info("register : " + user.getUsername() + " " + user.getPassword() + " " + user.getRole());
        if (!result.hasErrors()) {
            if (!this.userDetailService.userExists(user.getUsername())) {
                this.userDetailService.createUser(user);
                return ResponseEntity.ok().body(new Response("Account successfully created"));
            } else {
                return ResponseEntity.ok().body(new Response("Username already exists"));
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
