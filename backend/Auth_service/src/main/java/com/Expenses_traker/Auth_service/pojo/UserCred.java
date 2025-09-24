package com.Expenses_traker.Auth_service.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCred implements UserInterface {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Long userId;  // FK to userDetails.id
}
