package com.Auth_service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.Auth_service.util.Entity.UserCred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.Auth_service.DBHandler.UserCredApiHandler;
import com.Auth_service.pojo.AuthRequest;
import com.Auth_service.pojo.RegisteCheckMessage;
import com.Auth_service.pojo.RegisterCheckResponce;
import com.Auth_service.pojo.UserDBResponse;

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
        UserDBResponse response;
        if  (request.getUserName() == null || request.getUserName().isEmpty()){
            response = userCredApiHandler.findUserCredByGmail(request.getEmail());
        }
        else {
            response = userCredApiHandler.findUserCredByUsername(request.getUserName());
        }

        System.out.println("Response from UserCred service: " + response);
        if (response.isError()) {
            return ResponseEntity.ok(util.userErrorResponse(response.getMessage()));
        }

        UserCred userCred = (UserCred) response.getUserData().get(0);
        if (!request.getPassword().equals(userCred.getPassword())) {
            return ResponseEntity.ok(util.userErrorResponse("Incorrect password"));
        }

        return util.responseWithJWT(userCred);
    }

    public ResponseEntity<RegisterCheckResponce> RegisterProsser(UserCred request) {
        List<RegisteCheckMessage> errorData = new ArrayList<>();

        // Use a map of field name → value for validation
        Map<String, String> fields = Map.of(
                "Password", request.getPassword(),
                "UserName", request.getUserName(),
                "Email", request.getEmail());

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
        RegisterCheckResponce dbResponce = userCredApiHandler.register(request);
        if (dbResponce.isError()) {
            return ResponseEntity.ok(
                new RegisterCheckResponce("failed", "Validation failed", true, null,  dbResponce.getErrorData()));
        }
        if (dbResponce.getErrorData().size() > 0 || dbResponce.isError()) {
            return ResponseEntity.ok(new RegisterCheckResponce("failed",dbResponce.getMessage() , true, null, errorData));
        }
        return ResponseEntity.ok(dbResponce);
    }


    public ResponseEntity<UserDBResponse> forgotPassword(String email, String username){
        UserDBResponse res = null;
        if ( null == email && null == username){
            return ResponseEntity.ok(util.userErrorResponse("Email or username should provide"));
        } else if (null == email) {
            res = userCredApiHandler.findUserCredByUsername(username);
            if (res.isError() || res.getUserData().isEmpty()) {
                return ResponseEntity.ok(util.userErrorResponse("An error while find email with userName \\n please provide email"));
            }
            email = res.getUserData().get(0).getEmail();
        }
        if(null != email && null == res){
            res = userCredApiHandler.findUserCredByGmail(email);
        }
        return ResponseEntity.ok(util.userSuccessResponseList("A email "))
    }
}
