package com.Expenses_traker.Auth_service.Services;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
// import org.apache.tomcat.util.http.parser.MediaType; // Remove this import
import  org.springframework.http.*;

import com.Expenses_traker.Auth_service.pojo.UserCred;


@Service
public class TestDB {

    @Autowired
    private RestTemplate restTemplate;


    public List<UserCred> getTestData() {
        //String apiUrl = "http://localhost:8081/TestData";
        String apiUrl = "http://dbservice:8081/TestData";
        UserCred[] testDataArray = restTemplate.getForObject(apiUrl, UserCred[].class);
        List<UserCred> testDataList = java.util.Arrays.asList(testDataArray);
        return testDataList;
    }
}