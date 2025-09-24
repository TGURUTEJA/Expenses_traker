package com.Expenses_traker.Auth_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest implements UserInterface {
    private String userName;
    private String password;
    private String email;
}
