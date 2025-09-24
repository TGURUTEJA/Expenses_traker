package com.expenses_traker.db_service.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import com.expenses_traker.db_service.pojo.UserInterface;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("usercred") // matches table name
public class UserCred implements UserInterface {

    @Id
    private Long id;

    @Column("username")
    private String userName;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("user_id")
    private Long userId;  // FK to userDetails.id
}
