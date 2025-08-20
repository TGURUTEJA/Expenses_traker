package com.Expenses_traker.Auth_service.util;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Map;

import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.Expenses_traker.Auth_service.pojo.UserCred;

import ch.qos.logback.core.util.StringUtil;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class Util {

    private final JwtEncoder jwtEncoder;

    public boolean validateRequest(UserCred request) {
        if (StringUtil.isNullOrEmpty(request.getUserName())  || StringUtil.isNullOrEmpty(request.getEmail()) || StringUtil.isNullOrEmpty(request.getPassword())) {
            return false;
        }
        // Add more validation rules as needed
        return true;
    }
    
    public String generateToken(String subject, Map<String, Object> customClaims) {
        Instant now = Instant.now();
        JwtClaimsSet.Builder claims = JwtClaimsSet.builder()
                .issuer("auth-service")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(subject);

        if (customClaims != null) {
            customClaims.forEach(claims::claim);
        }

        JwsHeader header = JwsHeader.with(() -> "RS256").build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims.build())).getTokenValue();
    }


}
    

