package com.expenses_traker.db_service.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {
    @GetMapping("/")
    public String getUser() {
        return "this is DB service";
    }
    
}
