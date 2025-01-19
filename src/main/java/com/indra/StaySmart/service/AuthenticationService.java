package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.AuthRegisterRequest;
import com.indra.StaySmart.dto.request.AuthRequest;
import com.indra.StaySmart.entity.AppUser;
import com.indra.StaySmart.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserInfoRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthenticationService(UserInfoRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public String register(AuthRegisterRequest request) {
        // Check if user already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Username already taken!";
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(request.getUsername());
        appUser.setEmail(request.getEmail());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));

        // Assign role based on request or default to USER
        appUser.setRole("USER");

        // Set the role based on the input
//        appUser.setRole(request.getRole());  // Dynamic role assignment

        userRepository.save(appUser);
        return "User registered successfully!";
    }


    public String authenticate(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Authentication successful!";
    }
}

