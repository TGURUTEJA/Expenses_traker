package com.Auth_service.DBHandler;
import com.Auth_service.pojo.RegisteCheckMessage;
import com.Auth_service.pojo.RegisterCheckResponce;
import com.Auth_service.pojo.RegisterRequest;
import com.Auth_service.pojo.UserDBResponse;
import com.Auth_service.util.Entity.UserCred;
import com.Auth_service.util.Repository.UserCredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserCredDBHandler {

    @Autowired
    UserCredRepository userCredRepository;
    @Autowired
    DBHepler Healper;


    // -------------------- UserCred --------------------

    public UserDBResponse findAllUserCreds() {
        try {
            List<UserCred> list = (List<UserCred>) userCredRepository.findAll();
            return Healper.userSuccessResponseList("All user creds retrieved", list);
        } catch (Exception e) {
            return Healper.userErrorResponse("Error occurred: " + e.getMessage());
        }
    }

    public UserDBResponse findUserCredById(Long id) {
        try {
            Optional<UserCred> optionalUser = userCredRepository.findById(id);
            if (optionalUser.isPresent()) {
                return Healper.userSuccessResponseList("User with id " + id + " found",
                        List.of((UserCred) optionalUser.get()));
            } else {
                return Healper.userErrorResponse("No user found with id " + id);
            }
        } catch (Exception e) {
            return Healper.userErrorResponse("Error occurred: " + e.getMessage());
        }
    }

    public UserDBResponse saveUserCred(UserCred userCred) {
        try {
            UserCred saved = userCredRepository.save(userCred);
            return Healper.userSuccessResponseList("User " + saved.getUserName() + " saved",
                    List.of((UserCred) saved));
        } catch (Exception e) {
            return Healper.userErrorResponse("Error occurred while saving: " + e.getMessage());
        }
    }

    public UserDBResponse deleteUserCredById(Long id) {
        try {
            if (userCredRepository.existsById(id)) {
                userCredRepository.deleteById(id);
                return Healper.userSuccessResponseList("User with id " + id + " deleted", List.of());
            } else {
                return Healper.userErrorResponse("No user found with id " + id);
            }
        } catch (Exception e) {
            return Healper.userErrorResponse("Error occurred while deleting: " + e.getMessage());
        }
    }

    public UserDBResponse findUserCredByGmail(String email) {
        try {
            Optional<UserCred> optionalUser = userCredRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                return Healper.userSuccessResponseList("User " + email + " found",
                        List.of((UserCred) optionalUser.get()));
            } else {
                return Healper.userErrorResponse("No user found with email " + email);
            }
        } catch (Exception e) {
            return Healper.userErrorResponse("Error occurred: " + e.getMessage());
        }
    }

    public UserDBResponse findUserCredByUsername(String username) {
        try {
            Optional<UserCred> optionalUser = userCredRepository.findByUserName(username);
            if (optionalUser.isPresent()) {
                return Healper.userSuccessResponseList("User " + username + " found",
                        List.of((UserCred) optionalUser.get()));
            } else {
                return Healper.userErrorResponse("No user found with username " + username);
            }
        } catch (Exception e) {
            return Healper.userErrorResponse("Error occurred: " + e.getMessage());
        }
    }

    public RegisterCheckResponce register(RegisterRequest request) {
        RegisteCheckMessage emailCheck;
        RegisteCheckMessage usernameCheck;
        List<RegisteCheckMessage> messages = new ArrayList<>();

        try {
            var optionalEmailUser = userCredRepository.findByEmail(request.getEmail());
            if (optionalEmailUser.isPresent()) {
                emailCheck = new RegisteCheckMessage("email", true, "An account exists with this Email");
            } else {
                emailCheck = new RegisteCheckMessage("email", false, "No account exists with this Email");
            }
        } catch (Exception e) {
            emailCheck = new RegisteCheckMessage("email", false, "An error occurred while checking Email");
        }

        try {
            var optionalUsernameUser = userCredRepository.findByUserName(request.getUserName());
            if (optionalUsernameUser.isPresent()) {
                usernameCheck = new RegisteCheckMessage("username", true, "An account exists with this Username");
            } else {
                usernameCheck = new RegisteCheckMessage("username", false, "No account exists with this Username");
            }
        } catch (Exception e) {
            usernameCheck = new RegisteCheckMessage("username", false, "An error occurred while checking Username");
        }

        messages.add(emailCheck);
        messages.add(usernameCheck);

        boolean hasError = emailCheck.isError() || usernameCheck.isError();

        if (hasError) {
            return new RegisterCheckResponce(
                    "FAILED",
                    "Validation errors found",
                    true,
                    null,
                    messages);
        } else {
            return saveUserData(request);
        }
    }

    public RegisterCheckResponce saveUserData(RegisterRequest request) {
        try {
            UserCred requestData = new UserCred(request.getUserName(), request.getEmail(), request.getPassword());
            UserCred savedDetails = userCredRepository.save(requestData);
            return  new RegisterCheckResponce(
                    "SUCCESS",
                    "User registered successfully",
                    false,
                    List.of(savedDetails),
                    List.of());
        } catch (Exception e) {
            return new RegisterCheckResponce(
                    "FAILED",
                    "Error while saving credentials: " + e.getMessage(),
                    true,
                    null,
                    null);
        }
    }

}

