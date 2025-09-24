package com.Exp_traking.App_service.FunctionalInterfaces;

import org.springframework.web.server.ServerWebExchange;

import com.Exp_traking.App_service.Pojo.UserRequest;
import com.Exp_traking.App_service.Pojo.UserResponse;

import reactor.core.publisher.Mono;

public interface EXpService {
    Mono<UserResponse> getData(ServerWebExchange exchange,UserRequest userRequest);
} 
