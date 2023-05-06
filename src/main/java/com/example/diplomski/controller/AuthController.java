package com.example.diplomski.controller;

import com.example.diplomski.dto.*;
import com.example.diplomski.model.User;
import com.example.diplomski.service.AccountService;
import com.example.diplomski.service.PDFService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        return accountService.login(loginRequest, response);
    }

    @PostMapping("/logout")
    public ResponseOk logout(HttpServletRequest request, HttpServletResponse response) {
        accountService.logout(request, response);
        return new ResponseOk("Success");
    }

    @PostMapping("/register")
    public ResponseOk register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        accountService.registerNutritionist(registrationRequest);
        return new ResponseOk("User registered successfully.");
    }

    @PostMapping("/register/verify")
    public ResponseOk verify(@Valid @RequestBody VerificationRequest verificationRequest) {
        accountService.verifyEmail(verificationRequest);
        return new ResponseOk("Email verified successfully.");
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public UserInfoResponse getLoggedUserInfo(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new UserInfoResponse(user);
    }
}
