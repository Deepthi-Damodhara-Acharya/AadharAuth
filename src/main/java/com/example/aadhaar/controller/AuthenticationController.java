package com.example.aadhaar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aadhaar.model.AadhaarOtpRequest;
import com.example.aadhaar.model.AadhaarRequest;
import com.example.aadhaar.model.ServiceResponse;
import com.example.aadhaar.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<ServiceResponse<String>> sendOtp(@Validated @RequestBody AadhaarRequest request) {
        ServiceResponse<String> response = authenticationService.sendOtp(request.getAadhaarNumber());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ServiceResponse<Boolean>> verifyOtp(@Validated @RequestBody AadhaarOtpRequest request) {
        ServiceResponse<Boolean> response = authenticationService.verifyOtp(request.getAadhaarNumber(), request.getOtp());
        return ResponseEntity.ok(response);
    }
}
