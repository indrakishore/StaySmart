package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.AuthRegisterRequest;
import com.indra.StaySmart.dto.request.AuthRequest;
import com.indra.StaySmart.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody AuthRequest request) {
        return authenticationService.authenticate(request);
    }
}
