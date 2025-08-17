package com.expenses_traker.db_service.Controller;

import com.expenses_traker.db_service.Entity.UserCred;
import com.expenses_traker.db_service.Entity.UserDetails;
import com.expenses_traker.db_service.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    // UserDetails endpoints
    @GetMapping("/")
    public String getAllUsers() {
        return "this is DB service for expenses tracker";
    }

    @GetMapping("/userDetails")
    public Flux<UserDetails> getAllUserDetails() {
        return userService.findAllUserDetails();
    }

    @GetMapping("/userDetails/{id}")
    public Mono<UserDetails> getUserDetailsById(@PathVariable Long id) {
        return userService.findUserDetailsById(id);
    }

    @PostMapping("/userDetails")
    public Mono<UserDetails> createUserDetails(@RequestBody UserDetails userDetails) {
        return userService.saveUserDetails(userDetails);
    }

    @DeleteMapping("/userDetails/{id}")
    public Mono<Void> deleteUserDetailsById(@PathVariable Long id) {
        return userService.deleteUserDetailsById(id);
    }

    // UserCred endpoints

    @GetMapping("/userCreds")
    public Flux<UserCred> getAllUserCreds() {
        return userService.findAllUserCreds();
    }

    @GetMapping("/userCreds/{id}")
    public Mono<UserCred> getUserCredById(@PathVariable Long id) {
        return userService.findUserCredById(id);
    }

    @PostMapping("/userCreds")
    public Mono<UserCred> createUserCred(@RequestBody UserCred userCred) {
        return userService.saveUserCred(userCred);
    }

    @DeleteMapping("/userCreds/{id}")
    public Mono<Void> deleteUserCredById(@PathVariable Long id) {
        return userService.deleteUserCredById(id);
    }
}
