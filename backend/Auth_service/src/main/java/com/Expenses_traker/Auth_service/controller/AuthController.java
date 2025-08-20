package com.Expenses_traker.Auth_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Expenses_traker.Auth_service.Services.AuthService;
import com.Expenses_traker.Auth_service.pojo.UserCred;

@RestController
@RequestMapping("/api/auth") // normalized base path
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;
    @GetMapping("/status")
    public String status() {
        return "Auth Service is running";
    }
    @PostMapping( value = "/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserCred userCred) {
        return authService.loginUser(userCred);
    }
    @PostMapping(value = "/register")
    public String register(@RequestBody UserCred userCred) {
        return authService.registerUser(userCred);
    }
}
