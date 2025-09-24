package com.Exp_traking.App_service.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.Exp_traking.App_service.Apicalls.UserCredAPIHandelar;
import com.Exp_traking.App_service.FunctionalInterfaces.EXpService;
import com.Exp_traking.App_service.Pojo.UserRequest;
import com.Exp_traking.App_service.Pojo.UserResponse;

import reactor.core.publisher.Mono;

@Component
public class ExpServiceImp implements EXpService {

    @Autowired
    private UserCredAPIHandelar userCredAPIHandelar;

    public Mono<UserResponse> getData(ServerWebExchange exchange,UserRequest userRequest) {
        // Logic to fetch data based on userCred
        Mono<UserResponse> responce =  userCredAPIHandelar.customApicall("/userCreds/username/"+userRequest.getUsername(), UserResponse.class, "GET", userRequest);
        return responce;    
    }

    
}
