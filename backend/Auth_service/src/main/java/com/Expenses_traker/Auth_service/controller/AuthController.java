package com.Expenses_traker.Auth_service.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class AuthController {

    @GetMapping("/")
    public String status() {
        return "Auth Service is running";
    }

    @PostMapping("/login")
    public String login(@RequestBody String credentials) {
        // Placeholder for login logic
        return "Login endpoint hit";
    }

    @PostMapping("/register")
    public String register(@RequestBody String userDetails) {
        // Placeholder for registration logic
        return "Register endpoint hit";
    }
}