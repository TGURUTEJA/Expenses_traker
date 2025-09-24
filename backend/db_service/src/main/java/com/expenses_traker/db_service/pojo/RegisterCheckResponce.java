package com.expenses_traker.db_service.pojo;

import java.util.List;

import com.expenses_traker.db_service.Entity.UserCred;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCheckResponce {
    String Status;
    String message;
    boolean isError;    
    List<UserCred> userData;
    List<RegisteCheckMessage> errorData;
}

