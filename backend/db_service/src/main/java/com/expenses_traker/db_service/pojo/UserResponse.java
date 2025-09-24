package com.expenses_traker.db_service.pojo;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    String Status;
    String message;
    boolean isError;    
    List<UserInterface> userData;
}

