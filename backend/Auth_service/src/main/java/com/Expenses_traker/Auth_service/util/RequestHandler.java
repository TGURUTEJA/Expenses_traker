package com.Expenses_traker.Auth_service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.Expenses_traker.Auth_service.Apicalls.UserCredApiHandler;
import com.Expenses_traker.Auth_service.pojo.AuthRequest;
import com.Expenses_traker.Auth_service.pojo.RegisteCheckMessage;
import com.Expenses_traker.Auth_service.pojo.RegisterCheckResponce;
import com.Expenses_traker.Auth_service.pojo.RegisterRequest;
import com.Expenses_traker.Auth_service.pojo.UserCred;
import com.Expenses_traker.Auth_service.pojo.UserDBResponse;

@Component
public class RequestHandler {

    @Autowired
    private UserCredApiHandler userCredApiHandler;

    @Autowired
    private Util util;

    public ResponseEntity<UserDBResponse> loginProsser(AuthRequest request) {
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return ResponseEntity.ok(util.userErrorResponse("Password required"));
        }
        if ((request.getUserName() == null || request.getUserName().isEmpty())
                && (request.getEmail() == null || request.getEmail().isEmpty())) {
            return ResponseEntity.ok(util.userErrorResponse("Username or Email required"));
        }

        String url = (request.getUserName() == null || request.getUserName().isEmpty())
                ? "/userCreds/gmail/" + request.getEmail()
                : "/userCreds/username/" + request.getUserName();

        UserDBResponse response = userCredApiHandler.callApi(url, HttpMethod.GET, null, UserDBResponse.class);

        if (response.isError()) {
            return ResponseEntity.ok(util.userErrorResponse(response.getMessage()));
        }

        UserCred userCred = (UserCred) response.getUserData().get(0);
        if (!request.getPassword().equals(userCred.getPassword())) {
            return ResponseEntity.ok(util.userErrorResponse("Incorrect password"));
        }

        return util.responseWithJWT(userCred);
    }

    public ResponseEntity<RegisterCheckResponce> RegisterProsser(RegisterRequest request) {
        List<RegisteCheckMessage> errorData = new ArrayList<>();

        // Use a map of field name → value for validation
        Map<String, String> fields = Map.of(
                "Password", request.getPassword(),
                "UserName", request.getUserName(),
                "Email", request.getEmail(),
                "FirstName", request.getFirstName(),
                "LastName", request.getLastName(),
                "DataOfBirth", request.getBirthDate().toString());

        // Iterate and check null/empty using Stream
        fields.forEach((field, value) -> Optional.ofNullable(value)
                .filter(v -> !v.isBlank())
                .orElseGet(() -> {
                    errorData.add(new RegisteCheckMessage(
                            field, true, field + " cannot be empty"));
                    return null;
                }));
        if (errorData.size() > 0) {
            return ResponseEntity.ok(new RegisterCheckResponce("faild", "not proper request", true, null, errorData));
        }
        
        String registerUrl = "/userCreds/register";
        RegisterCheckResponce dbResponce = userCredApiHandler.callApi(registerUrl, HttpMethod.POST, request,
                RegisterCheckResponce.class);
        if (dbResponce.isError()) {
            return ResponseEntity.ok(
                new RegisterCheckResponce("failed", "Validation failed", true, null,  dbResponce.getErrorData()));
        }
        if (dbResponce.getErrorData().size() > 0 || dbResponce.isError()) {
            return ResponseEntity.ok(new RegisterCheckResponce("failed",dbResponce.getMessage() , true, null, errorData));
        }
        return ResponseEntity.ok(dbResponce);
    }
}
