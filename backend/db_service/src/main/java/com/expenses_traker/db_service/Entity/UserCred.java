package com.expenses_traker.db_service.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("usercred")
public class UserCred {

    @Id
    private Long id;
    private String email;
    private String UserName;
    private String password;
    private Boolean logged_in;
    private String user_token;
    private LocalDateTime token_expiration;
}
