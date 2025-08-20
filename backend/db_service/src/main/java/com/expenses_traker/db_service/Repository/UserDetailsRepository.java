package com.expenses_traker.db_service.Repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.expenses_traker.db_service.Entity.UserCred;
import com.expenses_traker.db_service.Entity.UserDetails;

import reactor.core.publisher.Mono;


@Repository
public interface UserDetailsRepository extends ReactiveCrudRepository<UserDetails, Long> {
    // Additional query methods can be defined here if needed
    // Find user credentials by email
    Mono<UserCred> findByEmail(String email);

}
