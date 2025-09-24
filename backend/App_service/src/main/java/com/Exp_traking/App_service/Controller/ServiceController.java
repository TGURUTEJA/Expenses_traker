package com.Exp_traking.App_service.Controller;


import java.rmi.server.ExportException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

import com.Exp_traking.App_service.Filter.JwtFilter;
import com.Exp_traking.App_service.FunctionalInterfaces.EXpService;
import com.Exp_traking.App_service.Pojo.UserResponse;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/") // normalized base path
public class ServiceController{

    @Autowired
    EXpService eXpService;

    @Autowired
    JwtFilter jwtFilter;

    @GetMapping("/status")
    public String status() {
        return "Service is running";
    }
    @GetMapping("/getData")
    public Mono<ResponseEntity<UserResponse>> getData(ServerWebExchange exchange) {
        System.out.println("Received /getData request");
        return jwtFilter.biFilterRequest(exchange, eXpService::getData);
    }
    @GetMapping("/getData1")
    public Mono<ResponseEntity<UserResponse>> getData1(ServerWebExchange exchange) {
        System.out.println("Received /getData request");
        UserResponse mockResponse = new UserResponse();
        mockResponse.setName("John Doe");
        mockResponse.setEmail("john.doe@example.com");
        return Mono.just(ResponseEntity.ok(mockResponse));
    }
}
