package com.expenses_traker.db_service.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import com.expenses_traker.db_service.pojo.UserInterface;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("userdetails")
public class UserDetails implements UserInterface {

    @Id
    private Long id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("birth_date")
    private LocalDate birthDate;

}
