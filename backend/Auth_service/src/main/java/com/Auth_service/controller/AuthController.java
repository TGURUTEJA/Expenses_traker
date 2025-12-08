package com.Auth_service.controller;

import java.util.Map;

import com.Auth_service.util.Entity.UserCred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Auth_service.Services.AuthService;
import com.Auth_service.pojo.AuthRequest;
import com.Auth_service.pojo.RegisterCheckResponce;
import com.Auth_service.pojo.UserDBResponse;

@RestController
@RequestMapping("/api/auth") // normalized base path
public class AuthController {

    @Autowired
    private AuthService authService;
    @GetMapping("/status")
    public String status() {
        return "Auth Service is running";
    }
    @PostMapping( value = "/login")
    public ResponseEntity<UserDBResponse> login(@RequestBody AuthRequest authRequest) {
        System.out.println("Login request received for user: " + authRequest.toString());
        return authService.loginUser(authRequest);
    }
    @PostMapping(value = "/register")
    public ResponseEntity<RegisterCheckResponce> register(@RequestBody UserCred request) {
        return authService.registerUser(request);
    }
    @PostMapping(value = "/logout")
    public ResponseEntity<Map<String, Object>> logout(@RequestBody Object request) {
        return authService.logoutUser();
    }
     @PostMapping(value = "/forgot-password")
     public ResponseEntity<UserDBResponse> forgotPassword(@RequestBody Map<String, String> request) {
         String email = request.get("email");
         String Username = request.get("username");
         return authService.forgotPassword(email,Username);
     }
}
