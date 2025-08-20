package com.Expenses_traker.Auth_service.Services;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Component;
import com.Expenses_traker.Auth_service.util.Util;
import com.Expenses_traker.Auth_service.Apicalls.UserCredAPIHandelar;
import com.Expenses_traker.Auth_service.pojo.UserCred;

@Component
public class AuthService {
    
    // This class will handle authentication logic
    // For example, methods for user registration, login, token generation, etc.

    @Autowired
    private Util util;

    @Autowired
    private UserCredAPIHandelar userCredAPIHandelar;

    public String registerUser(UserCred userCred) {
        if (util.validateRequest(userCred)) {
           userCredAPIHandelar.customApicall("/userDetails", UserCred.class, "Post", userCred);
           UserCred responce =  userCredAPIHandelar.customApicall("/userCreds", UserCred.class, "Post", userCred);
           return "User registered successfully with ID: " + responce.getId();
        } 
        return "Invalid user credentials";
    }

    public ResponseEntity<Map<String, Object>> loginUser(UserCred userCred) {
        UserCred existingUser = null;
        if((userCred.getPassword() == null) && (userCred.getUserName() == null && userCred.getEmail() == null)){
            return ResponseEntity.ok()
                .body(Map.of(
                        "success", false,
                        "message", "Username, Email, and Password cannot be null or empty."
                ));
        }
        if(userCred.getUserName() != null ){
            System.out.println("userCred.getUserName() is not null" + userCred.toString());
            existingUser = userCredAPIHandelar.customApicall("/userCreds/username/" + userCred.getUserName(), UserCred.class, "Get", null);
        }
        else if(userCred.getEmail() != null){
            System.out.println("userCred.getEmail() is not null" + userCred.toString());
            existingUser = userCredAPIHandelar.customApicall("/userCreds/gmail/" + userCred.getEmail(), UserCred.class, "Get", null);
        }
        System.out.println("Existing User: " + existingUser.toString());
        if (existingUser != null && existingUser.getPassword().equals(userCred.getPassword())) {
            return generateToken(userCred); 
        }
        return ResponseEntity.ok()
                .body(Map.of(
                        "success", false,
                        "message", "Invalid username/email or password."
                ));
    }

    public ResponseEntity<Map<String, Object>> generateToken(UserCred userCred) {
        // Logic to generate an authentication token for the user
         String subject = userCred.getUserName() != null ? userCred.getUserName() : userCred.getEmail();

        // Optional custom claims (e.g., roles/scopes)
        String jwt = util.generateToken(subject, Map.of("scope", "USER"));

        // HttpOnly, Secure cookie (adjust domain/secure flags for your environment)
          ResponseCookie jwtCookie = ResponseCookie.from("access_token", jwt)
                .httpOnly(true)
                .secure(true)            // set false only for local HTTP dev
                .path("/")
                .maxAge(Duration.ofHours(1)) // aligns with token expiry
                .sameSite("Strict")      // or "Lax" if needed for cross-site
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .body(Map.of(
                        "success", true,
                        "token_type", "Bearer",
                        "access_token", jwt,
                        "expires_in", 3600
                )); // Placeholder return value
    }

}
