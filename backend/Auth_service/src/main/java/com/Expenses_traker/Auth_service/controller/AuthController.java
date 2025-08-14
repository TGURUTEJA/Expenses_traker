package com.Expenses_traker.Auth_service.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Expenses_traker.Auth_service.Services.TestDB;
import com.Expenses_traker.Auth_service.pojo.testData;


@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private TestDB testDB;

    @GetMapping("/")
    public String status() {
        return "Auth Service is running";
    }

    @GetMapping("/login")
    public Object login() {
        try {
            List<testData> data = testDB.getTestData();
            return data;
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage() +"\\n"+ e;
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody String userDetails) {
        // Placeholder for registration logic
        return "Register endpoint hit";
    }
}