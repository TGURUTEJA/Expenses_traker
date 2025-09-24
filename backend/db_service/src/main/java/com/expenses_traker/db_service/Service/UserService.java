package com.expenses_traker.db_service.Service;

import com.expenses_traker.db_service.Entity.UserCred;
import com.expenses_traker.db_service.Entity.UserDetails;
import com.expenses_traker.db_service.Repository.UserCredRepository;
import com.expenses_traker.db_service.Repository.UserDetailsRepository;
import com.expenses_traker.db_service.pojo.RegisteCheckMessage;
import com.expenses_traker.db_service.pojo.RegisterCheckRequest;
import com.expenses_traker.db_service.pojo.RegisterCheckResponce;
import com.expenses_traker.db_service.pojo.RegisterRequest;
import com.expenses_traker.db_service.pojo.UserInterface;
import com.expenses_traker.db_service.pojo.UserResponse;
import com.expenses_traker.db_service.util.DTOMapper;
import com.expenses_traker.db_service.util.ResponseHealper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

@Service
@RequiredArgsConstructor
public class UserService {

        private final UserDetailsRepository userDetailsRepository;
        private final UserCredRepository userCredRepository;
        private final ResponseHealper responseHealper;

        @Autowired
        DTOMapper mapper;

        // -------------------- UserDetails --------------------

        public Mono<ResponseEntity<UserResponse>> findAllUserDetails() {
                return userDetailsRepository.findAll()
                                .cast(com.expenses_traker.db_service.pojo.UserInterface.class)
                                .collectList()
                                .map(list -> ResponseEntity.ok(
                                                responseHealper.userSuccessResponseList("All user details retrieved",
                                                                list)))
                                .switchIfEmpty(Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse("No user details found"))))
                                .onErrorResume(e -> Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "Error occurred: " + e.getMessage()))));
        }

        public Mono<ResponseEntity<UserResponse>> findUserDetailsById(Long id) {
                return userDetailsRepository.findById(id)
                                .map(user -> ResponseEntity.ok(
                                                responseHealper.userSuccessResponseList("User with id " + id + " found",
                                                                java.util.List.of((UserInterface) user))))
                                .switchIfEmpty(Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse("No user found with id " + id))))
                                .onErrorResume(e -> Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "Error occurred: " + e.getMessage()))));
        }

        public Mono<ResponseEntity<UserResponse>> saveUserDetails(UserDetails userDetails) {
                return userDetailsRepository.save(userDetails)
                                .map(saved -> ResponseEntity.ok(
                                                responseHealper.userSuccessResponseList("User saved successfully",
                                                                java.util.List.of((UserInterface) saved))))
                                .onErrorResume(e -> Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "Error occurred while saving: " + e.getMessage()))));
        }

        public Mono<ResponseEntity<UserResponse>> deleteUserDetailsById(Long id) {
                return userDetailsRepository.deleteById(id)
                                .then(Mono.just(ResponseEntity.ok(
                                                responseHealper.userSuccessResponseList(
                                                                "User with id " + id + " deleted",
                                                                java.util.List.of()))))
                                .switchIfEmpty(Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse("No user found with Email " + id))))
                                .onErrorResume(e -> Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "Error occurred while deleting: " + e.getMessage()))));
        }

        // public Mono<ResponseEntity<UserResponse>> findUserDetailsByGmail(String
        // email) {
        // return userDetailsRepository.findByEmail(email)
        // .then(Mono.just(ResponseEntity.ok(
        // responseHealper.userSuccessResponseList("User with email " + email + "
        // found",
        // java.util.List.of()))))
        // .onErrorResume(e -> Mono.just(ResponseEntity.ok(
        // responseHealper.userErrorResponse("Error occurred while deleting: " +
        // e.getMessage()))));
        // }

        // -------------------- UserCred --------------------

        public Mono<ResponseEntity<UserResponse>> findAllUserCreds() {
                return userCredRepository.findAll()
                                .cast(com.expenses_traker.db_service.pojo.UserInterface.class)
                                .collectList()
                                .map(list -> ResponseEntity.ok(
                                                responseHealper.userSuccessResponseList("All user creds retrieved",
                                                                list)))
                                .switchIfEmpty(Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse("No user creds found"))))
                                .onErrorResume(e -> Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "Error occurred: " + e.getMessage()))));
        }

        public Mono<ResponseEntity<UserResponse>> findUserCredById(Long id) {
                return userCredRepository.findById(id)
                                .map(user -> ResponseEntity.ok(
                                                responseHealper.userSuccessResponseList("User with id " + id + " found",
                                                                java.util.List.of((UserInterface) user))))
                                .switchIfEmpty(Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse("No user found with id " + id))))
                                .onErrorResume(e -> Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "Error occurred: " + e.getMessage()))));
        }

        public Mono<ResponseEntity<UserResponse>> saveUserCred(UserCred userCred) {
                return userCredRepository.save(userCred)
                                .map(saved -> ResponseEntity.ok(
                                                responseHealper.userSuccessResponseList(
                                                                "User " + saved.getUserName() + " saved",
                                                                java.util.List.of((UserInterface) saved))))
                                .onErrorResume(e -> Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "Error occurred while saving: " + e.getMessage()))));
        }

        public Mono<ResponseEntity<UserResponse>> deleteUserCredById(Long id) {
                return userCredRepository.deleteById(id)
                                .then(Mono.just(ResponseEntity.ok(
                                                responseHealper.userSuccessResponseList(
                                                                "User with id " + id + " deleted",
                                                                java.util.List.of()))))
                                .onErrorResume(e -> Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "Error occurred while deleting: " + e.getMessage()))));
        }

        public Mono<ResponseEntity<UserResponse>> findUserCredByGmail(String email) {
                return userCredRepository.findByEmail(email)
                                .map(user -> ResponseEntity.ok(
                                                responseHealper.userSuccessResponseList("User " + email + " found",
                                                                java.util.List.of((UserInterface) user))))
                                .switchIfEmpty(Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "No user found with username " + email))))
                                .onErrorResume(e -> Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "Error occurred: " + e.getMessage()))));
        }

        public Mono<ResponseEntity<UserResponse>> findUserCredByUsername(String username) {
                return userCredRepository.findByUserName(username)
                                .map(user -> ResponseEntity.ok(
                                                responseHealper.userSuccessResponseList("User " + username + " found",
                                                                java.util.List.of((UserInterface) user))))
                                .switchIfEmpty(Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "No user found with username " + username))))
                                .onErrorResume(e -> Mono.just(ResponseEntity.ok(
                                                responseHealper.userErrorResponse(
                                                                "Error occurred: " + e.getMessage()))));
        }

        public Mono<ResponseEntity<RegisterCheckResponce>> register(RegisterRequest request) {
                Mono<RegisteCheckMessage> emailCheck = userCredRepository.findByEmail(request.getEmail())
                                .map(user -> new RegisteCheckMessage("email", true,
                                                "An account exists with this Email"))
                                .switchIfEmpty(Mono.just(new RegisteCheckMessage("email", false,
                                                "No account exists with this Email")))
                                .onErrorResume(e -> Mono.just(new RegisteCheckMessage("email", false,
                                                "An error occurred while checking Email")));

                Mono<RegisteCheckMessage> usernameCheck = userCredRepository.findByUserName(request.getUserName())
                                .map(user -> new RegisteCheckMessage("username", true,
                                                "An account exists with this Username"))
                                .switchIfEmpty(Mono.just(new RegisteCheckMessage("username", false,
                                                "No account exists with this Username")))
                                .onErrorResume(e -> Mono.just(new RegisteCheckMessage("username", false,
                                                "An error occurred while checking Username")));

                return Mono.zip(emailCheck, usernameCheck)
                                .flatMap(tuple -> {
                                        List<RegisteCheckMessage> messages = new ArrayList<>();
                                        messages.add(tuple.getT1());
                                        messages.add(tuple.getT2());
                                        boolean hasError = tuple.getT1().isError() || tuple.getT2().isError();
                                        if (hasError) {
                                                RegisterCheckResponce errorResponse = new RegisterCheckResponce(
                                                                "FAILED",
                                                                "Validation errors found",
                                                                true,
                                                                null,
                                                                messages);
                                                return Mono.just(ResponseEntity.ok(errorResponse));
                                        } else {
                                                return saveUserData(request);
                                        }
                                });
        }

        public Mono<ResponseEntity<RegisterCheckResponce>> saveUserData(RegisterRequest request) {
                // Step 1: map request to userDetails
                UserDetails userDetails = mapper.requestToDetailsMapper(request);

                return userDetailsRepository.save(userDetails) // Save userDetails first
                                .flatMap(savedDetails -> {
                                        // Step 2: create UserCred from request + saved userDetails ID
                                        UserCred userCred = mapper.requestToCredMapper(request, savedDetails.getId());

                                        // Step 3: save UserCred
                                        return userCredRepository.save(userCred)
                                                        .map(savedCred -> {
                                                                // ✅ Success response
                                                                RegisterCheckResponce response = new RegisterCheckResponce(
                                                                                "SUCCESS",
                                                                                "User registered successfully",
                                                                                false,
                                                                                List.of(savedCred), // returning saved
                                                                                                    // userCred info
                                                                                List.of());
                                                                return ResponseEntity.ok(response);
                                                        })
                                                        // ✅ Handle error during userCred save
                                                        .onErrorResume(e -> {
                                                                RegisterCheckResponce errorResponse = new RegisterCheckResponce(
                                                                                "FAILED",
                                                                                "Error while saving credentials: "
                                                                                                + e.getMessage(),
                                                                                true,
                                                                                null,
                                                                                null);
                                                                return Mono.just(ResponseEntity.badRequest()
                                                                                .body(errorResponse));
                                                        });
                                })
                                // ✅ Handle error during userDetails save
                                .onErrorResume(e -> {
                                        RegisterCheckResponce errorResponse = new RegisterCheckResponce(
                                                        "FAILED",
                                                        "Error while saving user details: " + e.getMessage(),
                                                        true,
                                                        null,
                                                        null);
                                        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
                                });
        }

}
