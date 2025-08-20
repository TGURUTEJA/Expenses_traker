package com.expenses_traker.db_service.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("usercred") // matches PostgreSQL-folded table name
public class UserCred {

    @Id
    private Long id;

    @Column("email")
    private String email;

    @Column("username") // IMPORTANT: map userName -> username
    private String userName;

    @Column("password")
    private String password;

 
}
