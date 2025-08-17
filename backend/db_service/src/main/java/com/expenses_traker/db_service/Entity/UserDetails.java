package com.expenses_traker.db_service.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("userdetails")
public class UserDetails {

    @Id
    private Long id;
    private String firstname;
    private String secondname;
    private LocalDate age;
    private String email;
}
