package com.expenses_traker.db_service.Controller;

import com.expenses_traker.db_service.Entity.UserCred;
import com.expenses_traker.db_service.Entity.UserDetails;
import com.expenses_traker.db_service.Service.UserService;
import com.expenses_traker.db_service.pojo.RegisterCheckRequest;
import com.expenses_traker.db_service.pojo.RegisterCheckResponce;
import com.expenses_traker.db_service.pojo.RegisterRequest;
import com.expenses_traker.db_service.pojo.UserResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

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
    public Mono<ResponseEntity<UserResponse>> getAllUserDetails() {
        return userService.findAllUserDetails();
    }

    @GetMapping("/userDetails/{id}")
    public Mono<ResponseEntity<UserResponse>> getUserDetailsById(@PathVariable Long id) {
        return userService.findUserDetailsById(id);
    }

    @PostMapping("/userDetails")
    public Mono<ResponseEntity<UserResponse>> createUserDetails(@RequestBody UserDetails userDetails) {
        return userService.saveUserDetails(userDetails);
    }

    @DeleteMapping("/userDetails/{id}")
    public Mono<ResponseEntity<UserResponse>> deleteUserDetailsById(@PathVariable Long id) {
        return userService.deleteUserDetailsById(id);
    }

    // UserCred endpoints

    @GetMapping("/userCreds")
    public Mono<ResponseEntity<UserResponse>> getAllUserCreds() {
        return userService.findAllUserCreds();
    }

    @GetMapping("/userCreds/{id}")
    public Mono<ResponseEntity<UserResponse>> getUserCredById(@PathVariable Long id) {
        return userService.findUserCredById(id);
    }

    @PostMapping("/userCreds")
    public Mono<ResponseEntity<UserResponse>> createUserCred(@RequestBody UserCred userCred) {
        System.out.println("Creating UserCred: " + userCred);
        return userService.saveUserCred(userCred);
    }

    @DeleteMapping("/userCreds/{id}")
    public Mono<ResponseEntity<UserResponse>> deleteUserCredById(@RequestBody UserDetails userDetails) {
        if (userDetails.getId() == null) {
            UserResponse userResponse = new UserResponse();
            userResponse.setMessage("User ID is required for deletion.");
            userResponse.setStatus("failure");
            // userResponse.setError(true);
            return Mono.just(
                    ResponseEntity.ok(
                            userResponse));
        }
        return userService.deleteUserCredById(userDetails.getId());
    }

    @GetMapping("/userCreds/username/{username}")
    public Mono<ResponseEntity<UserResponse>> getUserCredByUsername(@PathVariable String username) {
        return userService.findUserCredByUsername(username);
    }

    @GetMapping("/userCreds/gmail/{gmail}")
    public Mono<ResponseEntity<UserResponse>> getUserCredByGmail(@PathVariable String gmail) {
        System.out.println("Fetching UserCred for gmail: " + gmail);
        return userService.findUserCredByGmail(gmail);
    }

    @PostMapping("/userCreds/register")
    public Mono<ResponseEntity<RegisterCheckResponce>> getRegister(@RequestBody RegisterRequest request){
        return userService.register(request);
    }

}
