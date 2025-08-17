package com.expenses_traker.db_service.Repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.expenses_traker.db_service.Entity.UserCred;

@Repository
public interface UserCredRepository extends ReactiveCrudRepository<UserCred, Long> {
    // Additional query methods can be defined here if needed
}
