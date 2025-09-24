package com.expenses_traker.db_service.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.expenses_traker.db_service.Entity.UserDetails;
import com.expenses_traker.db_service.pojo.UserInterface;
import com.expenses_traker.db_service.pojo.UserResponse;

@Component
public class ResponseHealper {

    public UserResponse userErrorResponse(String message) {
        UserResponse userResponse = new UserResponse();
        userResponse.setStatus("failed");
        userResponse.setMessage(message);
        userResponse.setUserData(null);
        userResponse.setError(true);
        return userResponse;
    }
     public UserResponse userSuccessResponseList(String message, List<UserInterface> userData) {
        UserResponse userResponse = new UserResponse();
        userResponse.setStatus("success");
        userResponse.setMessage(message);
        userResponse.setUserData(userData);
        userResponse.setError(false);
        return userResponse;
    }

}
