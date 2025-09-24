package com.Expenses_traker.Auth_service.util;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.Expenses_traker.Auth_service.Apicalls.UserCredApiHandler;
import com.Expenses_traker.Auth_service.pojo.AuthRequest;
import com.Expenses_traker.Auth_service.pojo.UserCred;
import com.Expenses_traker.Auth_service.pojo.UserDBResponse;
import com.Expenses_traker.Auth_service.pojo.UserDetails;
import com.Expenses_traker.Auth_service.pojo.UserInterface;

import ch.qos.logback.core.util.StringUtil;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class Util {

    private final JwtEncoder jwtEncoder;
    public String generateToken(String subject, UserCred customClaims) {
        Instant now = Instant.now();
        JwtClaimsSet.Builder claims = JwtClaimsSet.builder()
                .issuer("auth-service")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(subject);
        claims.claim("Email",customClaims.getEmail());
        claims.claim("userId", customClaims.getId());
        claims.claim("UserName", customClaims.getUserName());
        JwsHeader header = JwsHeader.with(() -> "RS256").build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims.build())).getTokenValue();
    }

     public UserDBResponse userErrorResponse(String message) {
        UserDBResponse userResponse = new UserDBResponse();
        userResponse.setStatus("failed");
        userResponse.setMessage(message);
        userResponse.setUserData(null);
        userResponse.setError(true);
        return userResponse;
    }
    
     public UserDBResponse userSuccessResponseList(String message, List<UserCred> userData) {
        UserDBResponse userResponse = new UserDBResponse();
        userResponse.setStatus("success");
        userResponse.setMessage(message);
        userResponse.setUserData(userData);
        userResponse.setError(false);
        return userResponse;
    }


    public ResponseEntity<UserDBResponse> responseWithJWT(UserCred response){
        String jwt = generateToken("JWTAUth",response);
         ResponseCookie jwtCookie = ResponseCookie.from("access_token", jwt)
                .httpOnly(true)
                .secure(true)          
                .path("/")
                .maxAge(Duration.ofHours(1))
                .sameSite("Strict")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .body(userSuccessResponseList( "Login Succefull",java.util.List.of((UserCred) response)));

    }



   


}
    

