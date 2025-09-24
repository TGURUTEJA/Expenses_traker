package com.Expenses_traker.Auth_service.pojo;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDBResponse {
    String Status;
    String message;
    boolean isError;    
    List<UserCred> userData;
}

