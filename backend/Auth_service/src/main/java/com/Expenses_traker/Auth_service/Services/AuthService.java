package com.Expenses_traker.Auth_service.Services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.Expenses_traker.Auth_service.util.RequestHandler;
import com.Expenses_traker.Auth_service.pojo.AuthRequest;
import com.Expenses_traker.Auth_service.pojo.RegisterCheckResponce;
import com.Expenses_traker.Auth_service.pojo.RegisterRequest;
import com.Expenses_traker.Auth_service.pojo.UserDBResponse;

@Component
public class AuthService {
    
    @Autowired
    RequestHandler requestHandler;


    public ResponseEntity<RegisterCheckResponce> registerUser(RegisterRequest request) {
        return requestHandler.RegisterProsser(request);
    }

    public ResponseEntity<UserDBResponse> loginUser(AuthRequest userCred) {
        return requestHandler.loginProsser(userCred);

    }

    public ResponseEntity<Map<String, Object>> logoutUser() {
        // Invalidate the token (if stored server-side) and clear the cookie
        ResponseCookie deleteCookie = ResponseCookie.from("access_token", "")
                .httpOnly(true)
                .secure(true)            // set false only for local HTTP dev
                .path("/")
                .maxAge(0)               // Expire immediately
                .sameSite("Strict")      // or "Lax" if needed for cross-site
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .body(Map.of(
                        "success", true,
                        "message", "Logged out successfully"
                ));
    }

}
