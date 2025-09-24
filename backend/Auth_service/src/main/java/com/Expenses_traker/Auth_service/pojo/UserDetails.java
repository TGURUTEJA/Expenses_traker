package com.Expenses_traker.Auth_service.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails implements UserInterface {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
