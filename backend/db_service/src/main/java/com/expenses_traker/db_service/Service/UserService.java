package com.expenses_traker.db_service.Service;

import com.expenses_traker.db_service.Entity.UserCred;
import com.expenses_traker.db_service.Entity.UserDetails;
import com.expenses_traker.db_service.Repository.UserCredRepository;
import com.expenses_traker.db_service.Repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserDetailsRepository userDetailsRepository;
    @Autowired
    private final UserCredRepository userCredRepository;

    // UserDetails methods

    public Flux<UserDetails> findAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public Mono<UserDetails> findUserDetailsById(Long id) {
        return userDetailsRepository.findById(id);
    }

    public Mono<UserDetails> saveUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public Mono<Void> deleteUserDetailsById(Long id) {
        return userDetailsRepository.deleteById(id);
    }

    // UserCred methods

    public Flux<UserCred> findAllUserCreds() {
        return userCredRepository.findAll();
    }

    public Mono<UserCred> findUserCredById(Long  id) {
        System.out.println("Finding UserCred by ID: " + id);
        return userCredRepository.findById(id);
    }

    public Mono<UserCred> saveUserCred(UserCred userCred) {
        return userCredRepository.save(userCred);
    }

    public Mono<Void> deleteUserCredById(Long  id) {
        return userCredRepository.deleteById(id);
    }
    public Mono<UserCred> findUserCredByGmail(String email) {
        return userCredRepository.findByEmail(email);
    }
    public Mono<UserCred> findUserCredByUsername(String username) {
        return userCredRepository.findByUserName(username); 
    }
}
