package com.Expenses_traker.Auth_service.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // matches PostgreSQL-folded table name
public class UserCred {

    private Long id;
    private String email;
    private String userName;
    private String password ;

}
