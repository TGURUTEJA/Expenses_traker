package com.expenses_traker.db_service.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.expenses_traker.db_service.Entity.testData;
import com.expenses_traker.db_service.Service.TestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {
    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String getUser() {
        return "this is DB service";
    }
    @GetMapping("/TestData")
    public List<testData> getTestData() {
            return testService.getTestdata();
    }
}
