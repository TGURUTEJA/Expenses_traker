package com.Expenses_traker.Auth_service.Apicalls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.Expenses_traker.Auth_service.pojo.UserCred;

@Component
public class UserCredAPIHandelar {
    @Autowired
    private RestTemplate restTemplate;

    private String domain = !true ? "http://dbservice:8081" : "http://localhost:8081";

    public <T> T customApicall(String apiUrl, Class<T> responseType, String method, UserCred userCred) {
        //String apiUrl = "http://localhost:8081/TestData"; 

        apiUrl = this.domain + apiUrl;
        if (method.equalsIgnoreCase("GET")) {
            return restTemplate.getForObject(apiUrl, responseType);
        }
        else if (method.equalsIgnoreCase("POST")) {
            System.out.println("UserCredAPIHandelar: " + apiUrl+ " " + userCred.toString());
            return restTemplate.postForObject(apiUrl,userCred, responseType);
        } else if (method.equalsIgnoreCase("PUT")) {
            restTemplate.put(apiUrl, userCred);
            return null;
        } else if (method.equalsIgnoreCase("DELETE")) {
            restTemplate.delete(apiUrl,userCred);
            return null;
        }
        return null;
    }

    
}
