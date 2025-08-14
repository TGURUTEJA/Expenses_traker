package com.Expenses_traker.Auth_service.Services;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
// import org.apache.tomcat.util.http.parser.MediaType; // Remove this import
import  org.springframework.http.*;

import com.Expenses_traker.Auth_service.pojo.testData;




@Service
public class TestDB {

    @Autowired
    private RestTemplate restTemplate;


    public List<testData> getTestData() {
        // Example API endpoint returning test data
        //String apiUrl = "http://localhost:8081/TestData";
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Host", "localhost:8081");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<testData[]> response = restTemplate.exchange(
            "http://db_service:8081/TestData",
            HttpMethod.GET,
            entity,
            testData[].class
        );
        testData[] testDataArrayFromExchange = response.getBody();
        List<testData> testDataListFromExchange = java.util.Arrays.asList(testDataArrayFromExchange);
        return testDataListFromExchange;
        // String apiUrl = "http://db_service:8081/TestData";
        // testData[] testDataArray = restTemplate.getForObject(apiUrl, testData[].class);
        // List<testData> testDataList = java.util.Arrays.asList(testDataArray);
        // return testDataList;
    }
}